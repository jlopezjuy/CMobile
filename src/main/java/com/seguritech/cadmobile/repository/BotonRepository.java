package com.seguritech.cadmobile.repository;

import com.seguritech.cadmobile.domain.Boton;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Boton entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BotonRepository extends JpaRepository<Boton,Long> {
    
}
