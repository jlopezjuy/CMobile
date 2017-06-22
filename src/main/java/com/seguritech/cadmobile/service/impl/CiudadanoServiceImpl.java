package com.seguritech.cadmobile.service.impl;

import com.seguritech.cadmobile.service.CiudadanoService;
import com.seguritech.cadmobile.domain.Ciudadano;
import com.seguritech.cadmobile.repository.CiudadanoRepository;
import com.seguritech.cadmobile.service.dto.CiudadanoDTO;
import com.seguritech.cadmobile.service.mapper.CiudadanoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Ciudadano.
 */
@Service
@Transactional
public class CiudadanoServiceImpl implements CiudadanoService{

    private final Logger log = LoggerFactory.getLogger(CiudadanoServiceImpl.class);

    private final CiudadanoRepository ciudadanoRepository;

    private final CiudadanoMapper ciudadanoMapper;

    public CiudadanoServiceImpl(CiudadanoRepository ciudadanoRepository, CiudadanoMapper ciudadanoMapper) {
        this.ciudadanoRepository = ciudadanoRepository;
        this.ciudadanoMapper = ciudadanoMapper;
    }

    /**
     * Save a ciudadano.
     *
     * @param ciudadanoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CiudadanoDTO save(CiudadanoDTO ciudadanoDTO) {
        log.debug("Request to save Ciudadano : {}", ciudadanoDTO);
        Ciudadano ciudadano = ciudadanoMapper.toEntity(ciudadanoDTO);
        ciudadano = ciudadanoRepository.save(ciudadano);
        return ciudadanoMapper.toDto(ciudadano);
    }

    /**
     *  Get all the ciudadanos.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CiudadanoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ciudadanos");
        return ciudadanoRepository.findAll(pageable)
            .map(ciudadanoMapper::toDto);
    }

    /**
     *  Get one ciudadano by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CiudadanoDTO findOne(Long id) {
        log.debug("Request to get Ciudadano : {}", id);
        Ciudadano ciudadano = ciudadanoRepository.findOne(id);
        return ciudadanoMapper.toDto(ciudadano);
    }

    /**
     *  Delete the  ciudadano by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ciudadano : {}", id);
        ciudadanoRepository.delete(id);
    }
}
