package com.seguritech.cadmobile.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.seguritech.cadmobile.service.CiudadanoService;
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
@RequestMapping("/api")
public class CiudadanoResource {

    private final Logger log = LoggerFactory.getLogger(CiudadanoResource.class);

    private static final String ENTITY_NAME = "ciudadano";

    private final CiudadanoService ciudadanoService;

    public CiudadanoResource(CiudadanoService ciudadanoService) {
        this.ciudadanoService = ciudadanoService;
    }

    /**
     * POST  /ciudadanos : Create a new ciudadano.
     *
     * @param ciudadanoDTO the ciudadanoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ciudadanoDTO, or with status 400 (Bad Request) if the ciudadano has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ciudadanos")
    @Timed
    public ResponseEntity<CiudadanoDTO> createCiudadano(@RequestBody CiudadanoDTO ciudadanoDTO) throws URISyntaxException {
        log.debug("REST request to save Ciudadano : {}", ciudadanoDTO);
        if (ciudadanoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ciudadano cannot already have an ID")).body(null);
        }
        CiudadanoDTO result = ciudadanoService.save(ciudadanoDTO);
        return ResponseEntity.created(new URI("/api/ciudadanos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ciudadanos : Updates an existing ciudadano.
     *
     * @param ciudadanoDTO the ciudadanoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ciudadanoDTO,
     * or with status 400 (Bad Request) if the ciudadanoDTO is not valid,
     * or with status 500 (Internal Server Error) if the ciudadanoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ciudadanos")
    @Timed
    public ResponseEntity<CiudadanoDTO> updateCiudadano(@RequestBody CiudadanoDTO ciudadanoDTO) throws URISyntaxException {
        log.debug("REST request to update Ciudadano : {}", ciudadanoDTO);
        if (ciudadanoDTO.getId() == null) {
            return createCiudadano(ciudadanoDTO);
        }
        CiudadanoDTO result = ciudadanoService.save(ciudadanoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ciudadanoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ciudadanos : get all the ciudadanos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ciudadanos in body
     */
    @GetMapping("/ciudadanos")
    @Timed
    public ResponseEntity<List<CiudadanoDTO>> getAllCiudadanos(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Ciudadanos");
        Page<CiudadanoDTO> page = ciudadanoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ciudadanos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /ciudadanos/:id : get the "id" ciudadano.
     *
     * @param id the id of the ciudadanoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ciudadanoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ciudadanos/{id}")
    @Timed
    public ResponseEntity<CiudadanoDTO> getCiudadano(@PathVariable Long id) {
        log.debug("REST request to get Ciudadano : {}", id);
        CiudadanoDTO ciudadanoDTO = ciudadanoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ciudadanoDTO));
    }

    /**
     * DELETE  /ciudadanos/:id : delete the "id" ciudadano.
     *
     * @param id the id of the ciudadanoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ciudadanos/{id}")
    @Timed
    public ResponseEntity<Void> deleteCiudadano(@PathVariable Long id) {
        log.debug("REST request to delete Ciudadano : {}", id);
        ciudadanoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
