package com.seguritech.cadmobile.service.mapper;

import com.seguritech.cadmobile.domain.*;
import com.seguritech.cadmobile.service.dto.EmergenciaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Emergencia and its DTO EmergenciaDTO.
 */
@Mapper(componentModel = "spring", uses = {CiudadanoMapper.class, })
public interface EmergenciaMapper extends EntityMapper <EmergenciaDTO, Emergencia> {

    @Mapping(source = "ciudadano.id", target = "ciudadanoId")
    EmergenciaDTO toDto(Emergencia emergencia); 

    @Mapping(source = "ciudadanoId", target = "ciudadano")
    Emergencia toEntity(EmergenciaDTO emergenciaDTO); 
    default Emergencia fromId(Long id) {
        if (id == null) {
            return null;
        }
        Emergencia emergencia = new Emergencia();
        emergencia.setId(id);
        return emergencia;
    }
}
