package com.seguritech.cadmobile.web.rest;

import com.seguritech.cadmobile.CadgatewayApp;

import com.seguritech.cadmobile.domain.Boton;
import com.seguritech.cadmobile.repository.BotonRepository;
import com.seguritech.cadmobile.service.BotonService;
import com.seguritech.cadmobile.service.dto.BotonDTO;
import com.seguritech.cadmobile.service.mapper.BotonMapper;
import com.seguritech.cadmobile.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BotonResource REST controller.
 *
 * @see BotonResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CadgatewayApp.class)
public class BotonResourceIntTest {

    private static final String DEFAULT_TELEFONO_MOVIL = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO_MOVIL = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO_BOTON = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_BOTON = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_LATITUD = new BigDecimal(1);
    private static final BigDecimal UPDATED_LATITUD = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LONGITUD = new BigDecimal(1);
    private static final BigDecimal UPDATED_LONGITUD = new BigDecimal(2);

    @Autowired
    private BotonRepository botonRepository;

    @Autowired
    private BotonMapper botonMapper;

    @Autowired
    private BotonService botonService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBotonMockMvc;

    private Boton boton;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BotonResource botonResource = new BotonResource(botonService);
        this.restBotonMockMvc = MockMvcBuilders.standaloneSetup(botonResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Boton createEntity(EntityManager em) {
        Boton boton = new Boton()
            .telefonoMovil(DEFAULT_TELEFONO_MOVIL)
            .codigoBoton(DEFAULT_CODIGO_BOTON)
            .latitud(DEFAULT_LATITUD)
            .longitud(DEFAULT_LONGITUD);
        return boton;
    }

    @Before
    public void initTest() {
        boton = createEntity(em);
    }

    @Test
    @Transactional
    public void createBoton() throws Exception {
        int databaseSizeBeforeCreate = botonRepository.findAll().size();

        // Create the Boton
        BotonDTO botonDTO = botonMapper.toDto(boton);
        restBotonMockMvc.perform(post("/rest/botons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(botonDTO)))
            .andExpect(status().isCreated());

        // Validate the Boton in the database
        List<Boton> botonList = botonRepository.findAll();
        assertThat(botonList).hasSize(databaseSizeBeforeCreate + 1);
        Boton testBoton = botonList.get(botonList.size() - 1);
        assertThat(testBoton.getTelefonoMovil()).isEqualTo(DEFAULT_TELEFONO_MOVIL);
        assertThat(testBoton.getCodigoBoton()).isEqualTo(DEFAULT_CODIGO_BOTON);
        assertThat(testBoton.getLatitud()).isEqualTo(DEFAULT_LATITUD);
        assertThat(testBoton.getLongitud()).isEqualTo(DEFAULT_LONGITUD);
    }

    @Test
    @Transactional
    public void createBotonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = botonRepository.findAll().size();

        // Create the Boton with an existing ID
        boton.setId(1L);
        BotonDTO botonDTO = botonMapper.toDto(boton);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBotonMockMvc.perform(post("/rest/botons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(botonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Boton> botonList = botonRepository.findAll();
        assertThat(botonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBotons() throws Exception {
        // Initialize the database
        botonRepository.saveAndFlush(boton);

        // Get all the botonList
        restBotonMockMvc.perform(get("/rest/botons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(boton.getId().intValue())))
            .andExpect(jsonPath("$.[*].telefonoMovil").value(hasItem(DEFAULT_TELEFONO_MOVIL.toString())))
            .andExpect(jsonPath("$.[*].codigoBoton").value(hasItem(DEFAULT_CODIGO_BOTON.toString())))
            .andExpect(jsonPath("$.[*].latitud").value(hasItem(DEFAULT_LATITUD.intValue())))
            .andExpect(jsonPath("$.[*].longitud").value(hasItem(DEFAULT_LONGITUD.intValue())));
    }

    @Test
    @Transactional
    public void getBoton() throws Exception {
        // Initialize the database
        botonRepository.saveAndFlush(boton);

        // Get the boton
        restBotonMockMvc.perform(get("/rest/botons/{id}", boton.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(boton.getId().intValue()))
            .andExpect(jsonPath("$.telefonoMovil").value(DEFAULT_TELEFONO_MOVIL.toString()))
            .andExpect(jsonPath("$.codigoBoton").value(DEFAULT_CODIGO_BOTON.toString()))
            .andExpect(jsonPath("$.latitud").value(DEFAULT_LATITUD.intValue()))
            .andExpect(jsonPath("$.longitud").value(DEFAULT_LONGITUD.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBoton() throws Exception {
        // Get the boton
        restBotonMockMvc.perform(get("/rest/botons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBoton() throws Exception {
        // Initialize the database
        botonRepository.saveAndFlush(boton);
        int databaseSizeBeforeUpdate = botonRepository.findAll().size();

        // Update the boton
        Boton updatedBoton = botonRepository.findOne(boton.getId());
        updatedBoton
            .telefonoMovil(UPDATED_TELEFONO_MOVIL)
            .codigoBoton(UPDATED_CODIGO_BOTON)
            .latitud(UPDATED_LATITUD)
            .longitud(UPDATED_LONGITUD);
        BotonDTO botonDTO = botonMapper.toDto(updatedBoton);

        restBotonMockMvc.perform(put("/rest/botons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(botonDTO)))
            .andExpect(status().isOk());

        // Validate the Boton in the database
        List<Boton> botonList = botonRepository.findAll();
        assertThat(botonList).hasSize(databaseSizeBeforeUpdate);
        Boton testBoton = botonList.get(botonList.size() - 1);
        assertThat(testBoton.getTelefonoMovil()).isEqualTo(UPDATED_TELEFONO_MOVIL);
        assertThat(testBoton.getCodigoBoton()).isEqualTo(UPDATED_CODIGO_BOTON);
        assertThat(testBoton.getLatitud()).isEqualTo(UPDATED_LATITUD);
        assertThat(testBoton.getLongitud()).isEqualTo(UPDATED_LONGITUD);
    }

    @Test
    @Transactional
    public void updateNonExistingBoton() throws Exception {
        int databaseSizeBeforeUpdate = botonRepository.findAll().size();

        // Create the Boton
        BotonDTO botonDTO = botonMapper.toDto(boton);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBotonMockMvc.perform(put("/rest/botons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(botonDTO)))
            .andExpect(status().isCreated());

        // Validate the Boton in the database
        List<Boton> botonList = botonRepository.findAll();
        assertThat(botonList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBoton() throws Exception {
        // Initialize the database
        botonRepository.saveAndFlush(boton);
        int databaseSizeBeforeDelete = botonRepository.findAll().size();

        // Get the boton
        restBotonMockMvc.perform(delete("/rest/botons/{id}", boton.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Boton> botonList = botonRepository.findAll();
        assertThat(botonList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Boton.class);
        Boton boton1 = new Boton();
        boton1.setId(1L);
        Boton boton2 = new Boton();
        boton2.setId(boton1.getId());
        assertThat(boton1).isEqualTo(boton2);
        boton2.setId(2L);
        assertThat(boton1).isNotEqualTo(boton2);
        boton1.setId(null);
        assertThat(boton1).isNotEqualTo(boton2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BotonDTO.class);
        BotonDTO botonDTO1 = new BotonDTO();
        botonDTO1.setId(1L);
        BotonDTO botonDTO2 = new BotonDTO();
        assertThat(botonDTO1).isNotEqualTo(botonDTO2);
        botonDTO2.setId(botonDTO1.getId());
        assertThat(botonDTO1).isEqualTo(botonDTO2);
        botonDTO2.setId(2L);
        assertThat(botonDTO1).isNotEqualTo(botonDTO2);
        botonDTO1.setId(null);
        assertThat(botonDTO1).isNotEqualTo(botonDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(botonMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(botonMapper.fromId(null)).isNull();
    }
}
