package com.seguritech.cadmobile.service;

import com.seguritech.cadmobile.service.dto.EmergenciaDTO;
import java.util.List;

/**
 * Service Interface for managing Emergencia.
 */
public interface EmergenciaService {

    /**
     * Save a emergencia.
     *
     * @param emergenciaDTO the entity to save
     * @return the persisted entity
     */
    EmergenciaDTO save(EmergenciaDTO emergenciaDTO);

    /**
     *  Get all the emergencias.
     *
     *  @return the list of entities
     */
    List<EmergenciaDTO> findAll();

    /**
     *  Get the "id" emergencia.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    EmergenciaDTO findOne(Long id);

    /**
     *  Delete the "id" emergencia.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
