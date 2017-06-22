package com.seguritech.cadmobile.repository;

import com.seguritech.cadmobile.domain.Ciudadano;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Ciudadano entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CiudadanoRepository extends JpaRepository<Ciudadano,Long> {

    Ciudadano findByTelefonoMovilAndCodigoVerificacion(String telefonoMovil, String codigoVerificacion);
}
