package com.seguritech.cadmobile.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.seguritech.cadmobile.service.BotonService;
import com.seguritech.cadmobile.service.dto.ReturnStatusDTO;
import com.seguritech.cadmobile.util.Constant;
import com.seguritech.cadmobile.web.rest.util.HeaderUtil;
import com.seguritech.cadmobile.service.dto.BotonDTO;
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
 * REST controller for managing Boton.
 */
@RestController
@RequestMapping("/rest")
public class BotonResource {

    private final Logger log = LoggerFactory.getLogger(BotonResource.class);

    private static final String ENTITY_NAME = "boton";

    private final BotonService botonService;

    public BotonResource(BotonService botonService) {
        this.botonService = botonService;
    }

    /**
     * POST  /botons : Create a new boton.
     *
     * @param botonDTO the botonDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new botonDTO, or with status 400 (Bad Request) if the boton has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dispositivo/movil/ciudadano/registroBoton")
    @Timed
    public ResponseEntity<ReturnStatusDTO> registroBoton(@RequestBody BotonDTO botonDTO) throws URISyntaxException {
        log.debug("REST request to save Boton : {}", botonDTO);
        if (botonDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new boton cannot already have an ID")).body(null);
        }
        BotonDTO result = botonService.save(botonDTO);
        ReturnStatusDTO returnStatus = new ReturnStatusDTO(Constant.CODIGO_0, Constant.CODIGO_0_MESSAGE);
        return new ResponseEntity<ReturnStatusDTO>(returnStatus, HttpStatus.CREATED);
    }


    /**
     * GET  /botons : get all the botons.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of botons in body
     */
    @GetMapping("/botons")
    @Timed
    public List<BotonDTO> getAllBotons() {
        log.debug("REST request to get all Botons");
        return botonService.findAll();
    }
}
