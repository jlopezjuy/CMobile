package com.seguritech.cadmobile.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.seguritech.cadmobile.service.dto.CiudadanoRegistroDTO;
import com.seguritech.cadmobile.service.CiudadanoService;
import com.seguritech.cadmobile.service.dto.VerificacionDTO;
import com.seguritech.cadmobile.util.Constant;
import com.seguritech.cadmobile.web.rest.util.HeaderUtil;
import com.seguritech.cadmobile.web.rest.util.PaginationUtil;
import com.seguritech.cadmobile.service.dto.CiudadanoDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Ciudadano.
 */
@RestController
@RequestMapping("/rest")
public class CiudadanoResource {

    private final Logger log = LoggerFactory.getLogger(CiudadanoResource.class);

    private static final String ENTITY_NAME = "ciudadano";

    private final CiudadanoService ciudadanoService;

    public CiudadanoResource(CiudadanoService ciudadanoService) {
        this.ciudadanoService = ciudadanoService;
    }

    /**
     * GET  /dispositivo/movil/ciudadano/verificar : get verification status of a ciudadano.
     *
     * @param telefonoMovil
     * @param codigoVerfificacion
     * @return Verification status of a Ciudadano with status 200 (ok) and status.
     */
    @GetMapping("/dispositivo/movil/ciudadano/verificar")
    @Timed
    public ResponseEntity<VerificacionDTO> verificarRegistro(@RequestParam String telefonoMovil, @RequestParam String codigoVerfificacion) {
        log.debug("REST request to get a page of Ciudadanos");
        CiudadanoDTO page = ciudadanoService.verificarReg(telefonoMovil, codigoVerfificacion);
        VerificacionDTO verificacion = new VerificacionDTO(Constant.CODIGO_0, Constant.CODIGO_0_MESSAGE);
        return new ResponseEntity<VerificacionDTO>(verificacion, HttpStatus.OK);
    }

    /**
     * POST  /ciudadanos : Create a new ciudadano.
     *
     * @param ciudadanoDTO the ciudadanoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ciudadanoDTO, or with status 400 (Bad Request) if the ciudadano has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dispositivo/movil/ciudadano/registrar")
    @Timed
    public ResponseEntity<CiudadanoRegistroDTO> registroCiudadano(@RequestBody CiudadanoDTO ciudadanoDTO) throws URISyntaxException {
        log.debug("REST request to save Ciudadano : {}", ciudadanoDTO);
        try{
            if (ciudadanoDTO.getId() != null) {
                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ciudadano cannot already have an ID")).body(null);
            }
            CiudadanoDTO result = ciudadanoService.save(ciudadanoDTO);
            CiudadanoRegistroDTO ciudadano = new CiudadanoRegistroDTO(Long.valueOf(0),"Se registro correctamente el ciudadano", result.getId());
            return new ResponseEntity<CiudadanoRegistroDTO>(ciudadano, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<CiudadanoRegistroDTO>(new CiudadanoRegistroDTO(), HttpStatus.BAD_REQUEST);
        }

    }
}
