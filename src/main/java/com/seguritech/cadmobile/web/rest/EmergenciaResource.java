package com.seguritech.cadmobile.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.seguritech.cadmobile.service.EmergenciaService;
import com.seguritech.cadmobile.web.rest.util.HeaderUtil;
import com.seguritech.cadmobile.service.dto.EmergenciaDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @PostMapping("/emergencias")
    @Timed
    public ResponseEntity<EmergenciaDTO> createEmergencia(@RequestBody EmergenciaDTO emergenciaDTO) throws URISyntaxException {
        log.debug("REST request to save Emergencia : {}", emergenciaDTO);
        if (emergenciaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new emergencia cannot already have an ID")).body(null);
        }
        EmergenciaDTO result = emergenciaService.save(emergenciaDTO);
        return ResponseEntity.created(new URI("/rest/emergencias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /emergencias : Updates an existing emergencia.
     *
     * @param emergenciaDTO the emergenciaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emergenciaDTO,
     * or with status 400 (Bad Request) if the emergenciaDTO is not valid,
     * or with status 500 (Internal Server Error) if the emergenciaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/emergencias")
    @Timed
    public ResponseEntity<EmergenciaDTO> updateEmergencia(@RequestBody EmergenciaDTO emergenciaDTO) throws URISyntaxException {
        log.debug("REST request to update Emergencia : {}", emergenciaDTO);
        if (emergenciaDTO.getId() == null) {
            return createEmergencia(emergenciaDTO);
        }
        EmergenciaDTO result = emergenciaService.save(emergenciaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emergenciaDTO.getId().toString()))
            .body(result);
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

    /**
     * GET  /emergencias/:id : get the "id" emergencia.
     *
     * @param id the id of the emergenciaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emergenciaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/emergencias/{id}")
    @Timed
    public ResponseEntity<EmergenciaDTO> getEmergencia(@PathVariable Long id) {
        log.debug("REST request to get Emergencia : {}", id);
        EmergenciaDTO emergenciaDTO = emergenciaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(emergenciaDTO));
    }

    /**
     * DELETE  /emergencias/:id : delete the "id" emergencia.
     *
     * @param id the id of the emergenciaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/emergencias/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmergencia(@PathVariable Long id) {
        log.debug("REST request to delete Emergencia : {}", id);
        emergenciaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
