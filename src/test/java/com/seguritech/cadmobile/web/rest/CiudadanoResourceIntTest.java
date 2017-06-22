package com.seguritech.cadmobile.web.rest;

import com.seguritech.cadmobile.CadgatewayApp;

import com.seguritech.cadmobile.domain.Ciudadano;
import com.seguritech.cadmobile.repository.CiudadanoRepository;
import com.seguritech.cadmobile.service.CiudadanoService;
import com.seguritech.cadmobile.service.dto.CiudadanoDTO;
import com.seguritech.cadmobile.service.mapper.CiudadanoMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CiudadanoResource REST controller.
 *
 * @see CiudadanoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CadgatewayApp.class)
public class CiudadanoResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_PATERNO = "AAAAAAAAAA";
    private static final String UPDATED_PATERNO = "BBBBBBBBBB";

    private static final String DEFAULT_MATERNO = "AAAAAAAAAA";
    private static final String UPDATED_MATERNO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_NACIMIENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_NACIMIENTO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TELEFONO_MOVIL = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO_MOVIL = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO_FIJO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO_FIJO = "BBBBBBBBBB";

    private static final String DEFAULT_CALLE = "AAAAAAAAAA";
    private static final String UPDATED_CALLE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_COLONIA = "AAAAAAAAAA";
    private static final String UPDATED_COLONIA = "BBBBBBBBBB";

    private static final String DEFAULT_MUNICIPIO = "AAAAAAAAAA";
    private static final String UPDATED_MUNICIPIO = "BBBBBBBBBB";

    private static final String DEFAULT_CP = "AAAAAAAAAA";
    private static final String UPDATED_CP = "BBBBBBBBBB";

    private static final String DEFAULT_GENERO = "AAAAAAAAAA";
    private static final String UPDATED_GENERO = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_SANGUINEO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_SANGUINEO = "BBBBBBBBBB";

    private static final String DEFAULT_ALERGIAS = "AAAAAAAAAA";
    private static final String UPDATED_ALERGIAS = "BBBBBBBBBB";

    private static final String DEFAULT_PADECIMIENTOS = "AAAAAAAAAA";
    private static final String UPDATED_PADECIMIENTOS = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_LATITUD = new BigDecimal(1);
    private static final BigDecimal UPDATED_LATITUD = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LONGITUD = new BigDecimal(1);
    private static final BigDecimal UPDATED_LONGITUD = new BigDecimal(2);

    private static final String DEFAULT_CODIGO_VERFIFICACION = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_VERFIFICACION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ESTADO = false;
    private static final Boolean UPDATED_ESTADO = true;

    @Autowired
    private CiudadanoRepository ciudadanoRepository;

    @Autowired
    private CiudadanoMapper ciudadanoMapper;

    @Autowired
    private CiudadanoService ciudadanoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCiudadanoMockMvc;

    private Ciudadano ciudadano;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CiudadanoResource ciudadanoResource = new CiudadanoResource(ciudadanoService);
        this.restCiudadanoMockMvc = MockMvcBuilders.standaloneSetup(ciudadanoResource)
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
    public static Ciudadano createEntity(EntityManager em) {
        Ciudadano ciudadano = new Ciudadano()
            .nombre(DEFAULT_NOMBRE)
            .paterno(DEFAULT_PATERNO)
            .materno(DEFAULT_MATERNO)
            .email(DEFAULT_EMAIL)
            .fechaNacimiento(DEFAULT_FECHA_NACIMIENTO)
            .telefonoMovil(DEFAULT_TELEFONO_MOVIL)
            .telefonoFijo(DEFAULT_TELEFONO_FIJO)
            .calle(DEFAULT_CALLE)
            .numero(DEFAULT_NUMERO)
            .colonia(DEFAULT_COLONIA)
            .municipio(DEFAULT_MUNICIPIO)
            .cp(DEFAULT_CP)
            .genero(DEFAULT_GENERO)
            .tipoSanguineo(DEFAULT_TIPO_SANGUINEO)
            .alergias(DEFAULT_ALERGIAS)
            .padecimientos(DEFAULT_PADECIMIENTOS)
            .latitud(DEFAULT_LATITUD)
            .longitud(DEFAULT_LONGITUD)
            .codigoVerificacion(DEFAULT_CODIGO_VERFIFICACION)
            .estado(DEFAULT_ESTADO);
        return ciudadano;
    }

    @Before
    public void initTest() {
        ciudadano = createEntity(em);
    }

    @Test
    @Transactional
    public void createCiudadano() throws Exception {
        int databaseSizeBeforeCreate = ciudadanoRepository.findAll().size();

        // Create the Ciudadano
        CiudadanoDTO ciudadanoDTO = ciudadanoMapper.toDto(ciudadano);
        restCiudadanoMockMvc.perform(post("/rest/ciudadanos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ciudadanoDTO)))
            .andExpect(status().isCreated());

        // Validate the Ciudadano in the database
        List<Ciudadano> ciudadanoList = ciudadanoRepository.findAll();
        assertThat(ciudadanoList).hasSize(databaseSizeBeforeCreate + 1);
        Ciudadano testCiudadano = ciudadanoList.get(ciudadanoList.size() - 1);
        assertThat(testCiudadano.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testCiudadano.getPaterno()).isEqualTo(DEFAULT_PATERNO);
        assertThat(testCiudadano.getMaterno()).isEqualTo(DEFAULT_MATERNO);
        assertThat(testCiudadano.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCiudadano.getFechaNacimiento()).isEqualTo(DEFAULT_FECHA_NACIMIENTO);
        assertThat(testCiudadano.getTelefonoMovil()).isEqualTo(DEFAULT_TELEFONO_MOVIL);
        assertThat(testCiudadano.getTelefonoFijo()).isEqualTo(DEFAULT_TELEFONO_FIJO);
        assertThat(testCiudadano.getCalle()).isEqualTo(DEFAULT_CALLE);
        assertThat(testCiudadano.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testCiudadano.getColonia()).isEqualTo(DEFAULT_COLONIA);
        assertThat(testCiudadano.getMunicipio()).isEqualTo(DEFAULT_MUNICIPIO);
        assertThat(testCiudadano.getCp()).isEqualTo(DEFAULT_CP);
        assertThat(testCiudadano.getGenero()).isEqualTo(DEFAULT_GENERO);
        assertThat(testCiudadano.getTipoSanguineo()).isEqualTo(DEFAULT_TIPO_SANGUINEO);
        assertThat(testCiudadano.getAlergias()).isEqualTo(DEFAULT_ALERGIAS);
        assertThat(testCiudadano.getPadecimientos()).isEqualTo(DEFAULT_PADECIMIENTOS);
        assertThat(testCiudadano.getLatitud()).isEqualTo(DEFAULT_LATITUD);
        assertThat(testCiudadano.getLongitud()).isEqualTo(DEFAULT_LONGITUD);
        assertThat(testCiudadano.getCodigoVerificacion()).isEqualTo(DEFAULT_CODIGO_VERFIFICACION);
        assertThat(testCiudadano.isEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createCiudadanoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ciudadanoRepository.findAll().size();

        // Create the Ciudadano with an existing ID
        ciudadano.setId(1L);
        CiudadanoDTO ciudadanoDTO = ciudadanoMapper.toDto(ciudadano);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCiudadanoMockMvc.perform(post("/rest/ciudadanos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ciudadanoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Ciudadano> ciudadanoList = ciudadanoRepository.findAll();
        assertThat(ciudadanoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCiudadanos() throws Exception {
        // Initialize the database
        ciudadanoRepository.saveAndFlush(ciudadano);

        // Get all the ciudadanoList
        restCiudadanoMockMvc.perform(get("/rest/ciudadanos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ciudadano.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].paterno").value(hasItem(DEFAULT_PATERNO.toString())))
            .andExpect(jsonPath("$.[*].materno").value(hasItem(DEFAULT_MATERNO.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].fechaNacimiento").value(hasItem(DEFAULT_FECHA_NACIMIENTO.toString())))
            .andExpect(jsonPath("$.[*].telefonoMovil").value(hasItem(DEFAULT_TELEFONO_MOVIL.toString())))
            .andExpect(jsonPath("$.[*].telefonoFijo").value(hasItem(DEFAULT_TELEFONO_FIJO.toString())))
            .andExpect(jsonPath("$.[*].calle").value(hasItem(DEFAULT_CALLE.toString())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.toString())))
            .andExpect(jsonPath("$.[*].colonia").value(hasItem(DEFAULT_COLONIA.toString())))
            .andExpect(jsonPath("$.[*].municipio").value(hasItem(DEFAULT_MUNICIPIO.toString())))
            .andExpect(jsonPath("$.[*].cp").value(hasItem(DEFAULT_CP.toString())))
            .andExpect(jsonPath("$.[*].genero").value(hasItem(DEFAULT_GENERO.toString())))
            .andExpect(jsonPath("$.[*].tipoSanguineo").value(hasItem(DEFAULT_TIPO_SANGUINEO.toString())))
            .andExpect(jsonPath("$.[*].alergias").value(hasItem(DEFAULT_ALERGIAS.toString())))
            .andExpect(jsonPath("$.[*].padecimientos").value(hasItem(DEFAULT_PADECIMIENTOS.toString())))
            .andExpect(jsonPath("$.[*].latitud").value(hasItem(DEFAULT_LATITUD.intValue())))
            .andExpect(jsonPath("$.[*].longitud").value(hasItem(DEFAULT_LONGITUD.intValue())))
            .andExpect(jsonPath("$.[*].codigoVerfificacion").value(hasItem(DEFAULT_CODIGO_VERFIFICACION.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.booleanValue())));
    }

    @Test
    @Transactional
    public void getCiudadano() throws Exception {
        // Initialize the database
        ciudadanoRepository.saveAndFlush(ciudadano);

        // Get the ciudadano
        restCiudadanoMockMvc.perform(get("/rest/ciudadanos/{id}", ciudadano.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ciudadano.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.paterno").value(DEFAULT_PATERNO.toString()))
            .andExpect(jsonPath("$.materno").value(DEFAULT_MATERNO.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.fechaNacimiento").value(DEFAULT_FECHA_NACIMIENTO.toString()))
            .andExpect(jsonPath("$.telefonoMovil").value(DEFAULT_TELEFONO_MOVIL.toString()))
            .andExpect(jsonPath("$.telefonoFijo").value(DEFAULT_TELEFONO_FIJO.toString()))
            .andExpect(jsonPath("$.calle").value(DEFAULT_CALLE.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.toString()))
            .andExpect(jsonPath("$.colonia").value(DEFAULT_COLONIA.toString()))
            .andExpect(jsonPath("$.municipio").value(DEFAULT_MUNICIPIO.toString()))
            .andExpect(jsonPath("$.cp").value(DEFAULT_CP.toString()))
            .andExpect(jsonPath("$.genero").value(DEFAULT_GENERO.toString()))
            .andExpect(jsonPath("$.tipoSanguineo").value(DEFAULT_TIPO_SANGUINEO.toString()))
            .andExpect(jsonPath("$.alergias").value(DEFAULT_ALERGIAS.toString()))
            .andExpect(jsonPath("$.padecimientos").value(DEFAULT_PADECIMIENTOS.toString()))
            .andExpect(jsonPath("$.latitud").value(DEFAULT_LATITUD.intValue()))
            .andExpect(jsonPath("$.longitud").value(DEFAULT_LONGITUD.intValue()))
            .andExpect(jsonPath("$.codigoVerfificacion").value(DEFAULT_CODIGO_VERFIFICACION.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCiudadano() throws Exception {
        // Get the ciudadano
        restCiudadanoMockMvc.perform(get("/rest/ciudadanos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCiudadano() throws Exception {
        // Initialize the database
        ciudadanoRepository.saveAndFlush(ciudadano);
        int databaseSizeBeforeUpdate = ciudadanoRepository.findAll().size();

        // Update the ciudadano
        Ciudadano updatedCiudadano = ciudadanoRepository.findOne(ciudadano.getId());
        updatedCiudadano
            .nombre(UPDATED_NOMBRE)
            .paterno(UPDATED_PATERNO)
            .materno(UPDATED_MATERNO)
            .email(UPDATED_EMAIL)
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .telefonoMovil(UPDATED_TELEFONO_MOVIL)
            .telefonoFijo(UPDATED_TELEFONO_FIJO)
            .calle(UPDATED_CALLE)
            .numero(UPDATED_NUMERO)
            .colonia(UPDATED_COLONIA)
            .municipio(UPDATED_MUNICIPIO)
            .cp(UPDATED_CP)
            .genero(UPDATED_GENERO)
            .tipoSanguineo(UPDATED_TIPO_SANGUINEO)
            .alergias(UPDATED_ALERGIAS)
            .padecimientos(UPDATED_PADECIMIENTOS)
            .latitud(UPDATED_LATITUD)
            .longitud(UPDATED_LONGITUD)
            .codigoVerificacion(UPDATED_CODIGO_VERFIFICACION)
            .estado(UPDATED_ESTADO);
        CiudadanoDTO ciudadanoDTO = ciudadanoMapper.toDto(updatedCiudadano);

        restCiudadanoMockMvc.perform(put("/rest/ciudadanos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ciudadanoDTO)))
            .andExpect(status().isOk());

        // Validate the Ciudadano in the database
        List<Ciudadano> ciudadanoList = ciudadanoRepository.findAll();
        assertThat(ciudadanoList).hasSize(databaseSizeBeforeUpdate);
        Ciudadano testCiudadano = ciudadanoList.get(ciudadanoList.size() - 1);
        assertThat(testCiudadano.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testCiudadano.getPaterno()).isEqualTo(UPDATED_PATERNO);
        assertThat(testCiudadano.getMaterno()).isEqualTo(UPDATED_MATERNO);
        assertThat(testCiudadano.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCiudadano.getFechaNacimiento()).isEqualTo(UPDATED_FECHA_NACIMIENTO);
        assertThat(testCiudadano.getTelefonoMovil()).isEqualTo(UPDATED_TELEFONO_MOVIL);
        assertThat(testCiudadano.getTelefonoFijo()).isEqualTo(UPDATED_TELEFONO_FIJO);
        assertThat(testCiudadano.getCalle()).isEqualTo(UPDATED_CALLE);
        assertThat(testCiudadano.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testCiudadano.getColonia()).isEqualTo(UPDATED_COLONIA);
        assertThat(testCiudadano.getMunicipio()).isEqualTo(UPDATED_MUNICIPIO);
        assertThat(testCiudadano.getCp()).isEqualTo(UPDATED_CP);
        assertThat(testCiudadano.getGenero()).isEqualTo(UPDATED_GENERO);
        assertThat(testCiudadano.getTipoSanguineo()).isEqualTo(UPDATED_TIPO_SANGUINEO);
        assertThat(testCiudadano.getAlergias()).isEqualTo(UPDATED_ALERGIAS);
        assertThat(testCiudadano.getPadecimientos()).isEqualTo(UPDATED_PADECIMIENTOS);
        assertThat(testCiudadano.getLatitud()).isEqualTo(UPDATED_LATITUD);
        assertThat(testCiudadano.getLongitud()).isEqualTo(UPDATED_LONGITUD);
        assertThat(testCiudadano.getCodigoVerificacion()).isEqualTo(UPDATED_CODIGO_VERFIFICACION);
        assertThat(testCiudadano.isEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingCiudadano() throws Exception {
        int databaseSizeBeforeUpdate = ciudadanoRepository.findAll().size();

        // Create the Ciudadano
        CiudadanoDTO ciudadanoDTO = ciudadanoMapper.toDto(ciudadano);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCiudadanoMockMvc.perform(put("/rest/ciudadanos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ciudadanoDTO)))
            .andExpect(status().isCreated());

        // Validate the Ciudadano in the database
        List<Ciudadano> ciudadanoList = ciudadanoRepository.findAll();
        assertThat(ciudadanoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCiudadano() throws Exception {
        // Initialize the database
        ciudadanoRepository.saveAndFlush(ciudadano);
        int databaseSizeBeforeDelete = ciudadanoRepository.findAll().size();

        // Get the ciudadano
        restCiudadanoMockMvc.perform(delete("/rest/ciudadanos/{id}", ciudadano.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ciudadano> ciudadanoList = ciudadanoRepository.findAll();
        assertThat(ciudadanoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ciudadano.class);
        Ciudadano ciudadano1 = new Ciudadano();
        ciudadano1.setId(1L);
        Ciudadano ciudadano2 = new Ciudadano();
        ciudadano2.setId(ciudadano1.getId());
        assertThat(ciudadano1).isEqualTo(ciudadano2);
        ciudadano2.setId(2L);
        assertThat(ciudadano1).isNotEqualTo(ciudadano2);
        ciudadano1.setId(null);
        assertThat(ciudadano1).isNotEqualTo(ciudadano2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CiudadanoDTO.class);
        CiudadanoDTO ciudadanoDTO1 = new CiudadanoDTO();
        ciudadanoDTO1.setId(1L);
        CiudadanoDTO ciudadanoDTO2 = new CiudadanoDTO();
        assertThat(ciudadanoDTO1).isNotEqualTo(ciudadanoDTO2);
        ciudadanoDTO2.setId(ciudadanoDTO1.getId());
        assertThat(ciudadanoDTO1).isEqualTo(ciudadanoDTO2);
        ciudadanoDTO2.setId(2L);
        assertThat(ciudadanoDTO1).isNotEqualTo(ciudadanoDTO2);
        ciudadanoDTO1.setId(null);
        assertThat(ciudadanoDTO1).isNotEqualTo(ciudadanoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ciudadanoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ciudadanoMapper.fromId(null)).isNull();
    }
}
