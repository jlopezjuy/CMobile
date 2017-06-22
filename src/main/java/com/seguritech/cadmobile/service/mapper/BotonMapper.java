package com.seguritech.cadmobile.service.mapper;

import com.seguritech.cadmobile.domain.*;
import com.seguritech.cadmobile.service.dto.BotonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Boton and its DTO BotonDTO.
 */
@Mapper(componentModel = "spring", uses = {CiudadanoMapper.class, })
public interface BotonMapper extends EntityMapper <BotonDTO, Boton> {

    @Mapping(source = "ciudadano.id", target = "ciudadanoId")
    BotonDTO toDto(Boton boton); 

    @Mapping(source = "ciudadanoId", target = "ciudadano")
    Boton toEntity(BotonDTO botonDTO); 
    default Boton fromId(Long id) {
        if (id == null) {
            return null;
        }
        Boton boton = new Boton();
        boton.setId(id);
        return boton;
    }
}
