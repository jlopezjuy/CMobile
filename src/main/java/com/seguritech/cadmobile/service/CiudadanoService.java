package com.seguritech.cadmobile.service;

import com.seguritech.cadmobile.service.dto.CiudadanoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Ciudadano.
 */
public interface CiudadanoService {

    /**
     * Save a ciudadano.
     *
     * @param ciudadanoDTO the entity to save
     * @return the persisted entity
     */
    CiudadanoDTO save(CiudadanoDTO ciudadanoDTO);

    /**
     *  Get all the ciudadanos.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CiudadanoDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" ciudadano.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CiudadanoDTO findOne(Long id);

    /**
     *  Delete the "id" ciudadano.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     *
     * @param telefonoMovil
     * @param codigoVerfificacion
     * @return
     */
    CiudadanoDTO verificarReg(String telefonoMovil, String codigoVerfificacion);
}
