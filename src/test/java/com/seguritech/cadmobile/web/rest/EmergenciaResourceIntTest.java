package com.seguritech.cadmobile.web.rest;

import com.seguritech.cadmobile.CadgatewayApp;

import com.seguritech.cadmobile.domain.Emergencia;
import com.seguritech.cadmobile.repository.EmergenciaRepository;
import com.seguritech.cadmobile.service.EmergenciaService;
import com.seguritech.cadmobile.service.dto.EmergenciaDTO;
import com.seguritech.cadmobile.service.mapper.EmergenciaMapper;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EmergenciaResource REST controller.
 *
 * @see EmergenciaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CadgatewayApp.class)
public class EmergenciaResourceIntTest {

    private static final String DEFAULT_TELEFONO_MOVIL = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO_MOVIL = "BBBBBBBBBB";

    private static final String DEFAULT_ID_CORPORACION = "AAAAAAAAAA";
    private static final String UPDATED_ID_CORPORACION = "BBBBBBBBBB";

    private static final String DEFAULT_ID_SUBTIPO_EMERGENCIA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBTIPO_EMERGENCIA = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGEN = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGEN_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_CONTENT_TYPE = "image/png";

    private static final BigDecimal DEFAULT_LATITUD = new BigDecimal(1);
    private static final BigDecimal UPDATED_LATITUD = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LONGITUD = new BigDecimal(1);
    private static final BigDecimal UPDATED_LONGITUD = new BigDecimal(2);

    @Autowired
    private EmergenciaRepository emergenciaRepository;

    @Autowired
    private EmergenciaMapper emergenciaMapper;

    @Autowired
    private EmergenciaService emergenciaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEmergenciaMockMvc;

    private Emergencia emergencia;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EmergenciaResource emergenciaResource = new EmergenciaResource(emergenciaService);
        this.restEmergenciaMockMvc = MockMvcBuilders.standaloneSetup(emergenciaResource)
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
    public static Emergencia createEntity(EntityManager em) {
        Emergencia emergencia = new Emergencia()
            .telefonoMovil(DEFAULT_TELEFONO_MOVIL)
            .idCorporacion(DEFAULT_ID_CORPORACION)
            .idSubtipoEmergencia(DEFAULT_ID_SUBTIPO_EMERGENCIA)
            .imagen(DEFAULT_IMAGEN)
            .imagenContentType(DEFAULT_IMAGEN_CONTENT_TYPE)
            .latitud(DEFAULT_LATITUD)
            .longitud(DEFAULT_LONGITUD);
        return emergencia;
    }

    @Before
    public void initTest() {
        emergencia = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmergencia() throws Exception {
        int databaseSizeBeforeCreate = emergenciaRepository.findAll().size();

        // Create the Emergencia
        EmergenciaDTO emergenciaDTO = emergenciaMapper.toDto(emergencia);
        restEmergenciaMockMvc.perform(post("/rest/emergencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emergenciaDTO)))
            .andExpect(status().isCreated());

        // Validate the Emergencia in the database
        List<Emergencia> emergenciaList = emergenciaRepository.findAll();
        assertThat(emergenciaList).hasSize(databaseSizeBeforeCreate + 1);
        Emergencia testEmergencia = emergenciaList.get(emergenciaList.size() - 1);
        assertThat(testEmergencia.getTelefonoMovil()).isEqualTo(DEFAULT_TELEFONO_MOVIL);
        assertThat(testEmergencia.getIdCorporacion()).isEqualTo(DEFAULT_ID_CORPORACION);
        assertThat(testEmergencia.getIdSubtipoEmergencia()).isEqualTo(DEFAULT_ID_SUBTIPO_EMERGENCIA);
        assertThat(testEmergencia.getImagen()).isEqualTo(DEFAULT_IMAGEN);
        assertThat(testEmergencia.getImagenContentType()).isEqualTo(DEFAULT_IMAGEN_CONTENT_TYPE);
        assertThat(testEmergencia.getLatitud()).isEqualTo(DEFAULT_LATITUD);
        assertThat(testEmergencia.getLongitud()).isEqualTo(DEFAULT_LONGITUD);
    }

    @Test
    @Transactional
    public void createEmergenciaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emergenciaRepository.findAll().size();

        // Create the Emergencia with an existing ID
        emergencia.setId(1L);
        EmergenciaDTO emergenciaDTO = emergenciaMapper.toDto(emergencia);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmergenciaMockMvc.perform(post("/rest/emergencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emergenciaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Emergencia> emergenciaList = emergenciaRepository.findAll();
        assertThat(emergenciaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEmergencias() throws Exception {
        // Initialize the database
        emergenciaRepository.saveAndFlush(emergencia);

        // Get all the emergenciaList
        restEmergenciaMockMvc.perform(get("/rest/emergencias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emergencia.getId().intValue())))
            .andExpect(jsonPath("$.[*].telefonoMovil").value(hasItem(DEFAULT_TELEFONO_MOVIL.toString())))
            .andExpect(jsonPath("$.[*].idCorporacion").value(hasItem(DEFAULT_ID_CORPORACION.toString())))
            .andExpect(jsonPath("$.[*].idSubtipoEmergencia").value(hasItem(DEFAULT_ID_SUBTIPO_EMERGENCIA.toString())))
            .andExpect(jsonPath("$.[*].imagenContentType").value(hasItem(DEFAULT_IMAGEN_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN))))
            .andExpect(jsonPath("$.[*].latitud").value(hasItem(DEFAULT_LATITUD.intValue())))
            .andExpect(jsonPath("$.[*].longitud").value(hasItem(DEFAULT_LONGITUD.intValue())));
    }

    @Test
    @Transactional
    public void getEmergencia() throws Exception {
        // Initialize the database
        emergenciaRepository.saveAndFlush(emergencia);

        // Get the emergencia
        restEmergenciaMockMvc.perform(get("/rest/emergencias/{id}", emergencia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(emergencia.getId().intValue()))
            .andExpect(jsonPath("$.telefonoMovil").value(DEFAULT_TELEFONO_MOVIL.toString()))
            .andExpect(jsonPath("$.idCorporacion").value(DEFAULT_ID_CORPORACION.toString()))
            .andExpect(jsonPath("$.idSubtipoEmergencia").value(DEFAULT_ID_SUBTIPO_EMERGENCIA.toString()))
            .andExpect(jsonPath("$.imagenContentType").value(DEFAULT_IMAGEN_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen").value(Base64Utils.encodeToString(DEFAULT_IMAGEN)))
            .andExpect(jsonPath("$.latitud").value(DEFAULT_LATITUD.intValue()))
            .andExpect(jsonPath("$.longitud").value(DEFAULT_LONGITUD.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEmergencia() throws Exception {
        // Get the emergencia
        restEmergenciaMockMvc.perform(get("/rest/emergencias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmergencia() throws Exception {
        // Initialize the database
        emergenciaRepository.saveAndFlush(emergencia);
        int databaseSizeBeforeUpdate = emergenciaRepository.findAll().size();

        // Update the emergencia
        Emergencia updatedEmergencia = emergenciaRepository.findOne(emergencia.getId());
        updatedEmergencia
            .telefonoMovil(UPDATED_TELEFONO_MOVIL)
            .idCorporacion(UPDATED_ID_CORPORACION)
            .idSubtipoEmergencia(UPDATED_ID_SUBTIPO_EMERGENCIA)
            .imagen(UPDATED_IMAGEN)
            .imagenContentType(UPDATED_IMAGEN_CONTENT_TYPE)
            .latitud(UPDATED_LATITUD)
            .longitud(UPDATED_LONGITUD);
        EmergenciaDTO emergenciaDTO = emergenciaMapper.toDto(updatedEmergencia);

        restEmergenciaMockMvc.perform(put("/rest/emergencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emergenciaDTO)))
            .andExpect(status().isOk());

        // Validate the Emergencia in the database
        List<Emergencia> emergenciaList = emergenciaRepository.findAll();
        assertThat(emergenciaList).hasSize(databaseSizeBeforeUpdate);
        Emergencia testEmergencia = emergenciaList.get(emergenciaList.size() - 1);
        assertThat(testEmergencia.getTelefonoMovil()).isEqualTo(UPDATED_TELEFONO_MOVIL);
        assertThat(testEmergencia.getIdCorporacion()).isEqualTo(UPDATED_ID_CORPORACION);
        assertThat(testEmergencia.getIdSubtipoEmergencia()).isEqualTo(UPDATED_ID_SUBTIPO_EMERGENCIA);
        assertThat(testEmergencia.getImagen()).isEqualTo(UPDATED_IMAGEN);
        assertThat(testEmergencia.getImagenContentType()).isEqualTo(UPDATED_IMAGEN_CONTENT_TYPE);
        assertThat(testEmergencia.getLatitud()).isEqualTo(UPDATED_LATITUD);
        assertThat(testEmergencia.getLongitud()).isEqualTo(UPDATED_LONGITUD);
    }

    @Test
    @Transactional
    public void updateNonExistingEmergencia() throws Exception {
        int databaseSizeBeforeUpdate = emergenciaRepository.findAll().size();

        // Create the Emergencia
        EmergenciaDTO emergenciaDTO = emergenciaMapper.toDto(emergencia);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmergenciaMockMvc.perform(put("/rest/emergencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emergenciaDTO)))
            .andExpect(status().isCreated());

        // Validate the Emergencia in the database
        List<Emergencia> emergenciaList = emergenciaRepository.findAll();
        assertThat(emergenciaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEmergencia() throws Exception {
        // Initialize the database
        emergenciaRepository.saveAndFlush(emergencia);
        int databaseSizeBeforeDelete = emergenciaRepository.findAll().size();

        // Get the emergencia
        restEmergenciaMockMvc.perform(delete("/rest/emergencias/{id}", emergencia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Emergencia> emergenciaList = emergenciaRepository.findAll();
        assertThat(emergenciaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Emergencia.class);
        Emergencia emergencia1 = new Emergencia();
        emergencia1.setId(1L);
        Emergencia emergencia2 = new Emergencia();
        emergencia2.setId(emergencia1.getId());
        assertThat(emergencia1).isEqualTo(emergencia2);
        emergencia2.setId(2L);
        assertThat(emergencia1).isNotEqualTo(emergencia2);
        emergencia1.setId(null);
        assertThat(emergencia1).isNotEqualTo(emergencia2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmergenciaDTO.class);
        EmergenciaDTO emergenciaDTO1 = new EmergenciaDTO();
        emergenciaDTO1.setId(1L);
        EmergenciaDTO emergenciaDTO2 = new EmergenciaDTO();
        assertThat(emergenciaDTO1).isNotEqualTo(emergenciaDTO2);
        emergenciaDTO2.setId(emergenciaDTO1.getId());
        assertThat(emergenciaDTO1).isEqualTo(emergenciaDTO2);
        emergenciaDTO2.setId(2L);
        assertThat(emergenciaDTO1).isNotEqualTo(emergenciaDTO2);
        emergenciaDTO1.setId(null);
        assertThat(emergenciaDTO1).isNotEqualTo(emergenciaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(emergenciaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(emergenciaMapper.fromId(null)).isNull();
    }
}
