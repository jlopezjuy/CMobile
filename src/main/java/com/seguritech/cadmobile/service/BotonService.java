package com.seguritech.cadmobile.service;

import com.seguritech.cadmobile.service.dto.BotonDTO;
import java.util.List;

/**
 * Service Interface for managing Boton.
 */
public interface BotonService {

    /**
     * Save a boton.
     *
     * @param botonDTO the entity to save
     * @return the persisted entity
     */
    BotonDTO save(BotonDTO botonDTO);

    /**
     *  Get all the botons.
     *
     *  @return the list of entities
     */
    List<BotonDTO> findAll();

    /**
     *  Get the "id" boton.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    BotonDTO findOne(Long id);

    /**
     *  Delete the "id" boton.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     *
     * @param telefonoMovil
     * @return
     */
    Boolean validateCiudadano(String telefonoMovil);
}
