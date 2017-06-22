package com.seguritech.cadmobile.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.seguritech.cadmobile.service.EmergenciaService;
import com.seguritech.cadmobile.service.dto.ReturnStatusDTO;
import com.seguritech.cadmobile.util.Constant;
import com.seguritech.cadmobile.web.rest.util.HeaderUtil;
import com.seguritech.cadmobile.service.dto.EmergenciaDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Emergencia.
 */
@RestController
@RequestMapping("/rest")
public class EmergenciaResource {

    private final Logger log = LoggerFactory.getLogger(EmergenciaResource.class);

    private static final String ENTITY_NAME = "emergencia";

    private final EmergenciaService emergenciaService;

    public EmergenciaResource(EmergenciaService emergenciaService) {
        this.emergenciaService = emergenciaService;
    }


    /**
     * POST  /emergencias : Create a new emergencia.
     *
     * @param emergenciaDTO the emergenciaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emergenciaDTO, or with status 400 (Bad Request) if the emergencia has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dispositivo/movil/emergencia/peticion")
    @Timed
    public ResponseEntity<ReturnStatusDTO> peticionEmergencia(@RequestBody EmergenciaDTO emergenciaDTO) throws URISyntaxException {
        log.debug("REST request to save Emergencia : {}", emergenciaDTO);
        if (emergenciaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new emergencia cannot already have an ID")).body(null);
        }
        EmergenciaDTO result = emergenciaService.save(emergenciaDTO);
        ReturnStatusDTO returnStatus = new ReturnStatusDTO(Constant.CODIGO_0, Constant.CODIGO_0_MESSAGE_EMERGENCIA);
        return new ResponseEntity<ReturnStatusDTO>(returnStatus, HttpStatus.CREATED);
    }


    /**
     * GET  /emergencias : get all the emergencias.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of emergencias in body
     */
    @GetMapping("/emergencias")
    @Timed
    public List<EmergenciaDTO> getAllEmergencias() {
        log.debug("REST request to get all Emergencias");
        return emergenciaService.findAll();
    }
}
