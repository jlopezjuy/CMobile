package com.seguritech.cadmobile.service.mapper;

import com.seguritech.cadmobile.domain.*;
import com.seguritech.cadmobile.service.dto.CiudadanoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ciudadano and its DTO CiudadanoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CiudadanoMapper extends EntityMapper <CiudadanoDTO, Ciudadano> {
    
    @Mapping(target = "botons", ignore = true)
    @Mapping(target = "emeprgencias", ignore = true)
    Ciudadano toEntity(CiudadanoDTO ciudadanoDTO); 
    default Ciudadano fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setId(id);
        return ciudadano;
    }
}
