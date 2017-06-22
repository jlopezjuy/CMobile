package com.seguritech.cadmobile.service.impl;

import com.seguritech.cadmobile.service.EmergenciaService;
import com.seguritech.cadmobile.domain.Emergencia;
import com.seguritech.cadmobile.repository.EmergenciaRepository;
import com.seguritech.cadmobile.service.dto.EmergenciaDTO;
import com.seguritech.cadmobile.service.mapper.EmergenciaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Emergencia.
 */
@Service
@Transactional
public class EmergenciaServiceImpl implements EmergenciaService{

    private final Logger log = LoggerFactory.getLogger(EmergenciaServiceImpl.class);

    private final EmergenciaRepository emergenciaRepository;

    private final EmergenciaMapper emergenciaMapper;

    public EmergenciaServiceImpl(EmergenciaRepository emergenciaRepository, EmergenciaMapper emergenciaMapper) {
        this.emergenciaRepository = emergenciaRepository;
        this.emergenciaMapper = emergenciaMapper;
    }

    /**
     * Save a emergencia.
     *
     * @param emergenciaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EmergenciaDTO save(EmergenciaDTO emergenciaDTO) {
        log.debug("Request to save Emergencia : {}", emergenciaDTO);
        Emergencia emergencia = emergenciaMapper.toEntity(emergenciaDTO);
        emergencia = emergenciaRepository.save(emergencia);
        return emergenciaMapper.toDto(emergencia);
    }

    /**
     *  Get all the emergencias.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmergenciaDTO> findAll() {
        log.debug("Request to get all Emergencias");
        return emergenciaRepository.findAll().stream()
            .map(emergenciaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one emergencia by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EmergenciaDTO findOne(Long id) {
        log.debug("Request to get Emergencia : {}", id);
        Emergencia emergencia = emergenciaRepository.findOne(id);
        return emergenciaMapper.toDto(emergencia);
    }

    /**
     *  Delete the  emergencia by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Emergencia : {}", id);
        emergenciaRepository.delete(id);
    }
}
