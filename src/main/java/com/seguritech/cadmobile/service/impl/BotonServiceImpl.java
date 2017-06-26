package com.seguritech.cadmobile.service.impl;

import com.seguritech.cadmobile.domain.Ciudadano;
import com.seguritech.cadmobile.repository.CiudadanoRepository;
import com.seguritech.cadmobile.service.BotonService;
import com.seguritech.cadmobile.domain.Boton;
import com.seguritech.cadmobile.repository.BotonRepository;
import com.seguritech.cadmobile.service.dto.BotonDTO;
import com.seguritech.cadmobile.service.mapper.BotonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Boton.
 */
@Service
@Transactional
public class BotonServiceImpl implements BotonService{

    private final Logger log = LoggerFactory.getLogger(BotonServiceImpl.class);

    private final BotonRepository botonRepository;
    private final CiudadanoRepository ciudadanoRepository;

    private final BotonMapper botonMapper;

    private Ciudadano ciudadano;

    public BotonServiceImpl(BotonRepository botonRepository, BotonMapper botonMapper, CiudadanoRepository ciudadanoRepository) {
        this.botonRepository = botonRepository;
        this.botonMapper = botonMapper;
        this.ciudadanoRepository = ciudadanoRepository;
    }

    /**
     * Save a boton.
     *
     * @param botonDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BotonDTO save(BotonDTO botonDTO) {
        log.debug("Request to save Boton : {}", botonDTO);
        botonDTO.setCiudadanoId(this.ciudadano.getId());
        Boton boton = botonMapper.toEntity(botonDTO);
        boton = botonRepository.save(boton);
        return botonMapper.toDto(boton);
    }

    /**
     *  Get all the botons.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BotonDTO> findAll() {
        log.debug("Request to get all Botons");
        return botonRepository.findAll().stream()
            .map(botonMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one boton by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BotonDTO findOne(Long id) {
        log.debug("Request to get Boton : {}", id);
        Boton boton = botonRepository.findOne(id);
        return botonMapper.toDto(boton);
    }

    /**
     *  Delete the  boton by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Boton : {}", id);
        botonRepository.delete(id);
    }

    /**
     * Method to validate ciudadano
     * @param telefonoMovil
     * @return
     */
    @Override
    public Boolean validateCiudadano(String telefonoMovil) {
        this.ciudadano = ciudadanoRepository.findByTelefonoMovil(telefonoMovil);
        return (null != this.ciudadano);
    }
}
