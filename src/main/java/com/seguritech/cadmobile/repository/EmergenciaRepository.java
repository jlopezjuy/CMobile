package com.seguritech.cadmobile.repository;

import com.seguritech.cadmobile.domain.Emergencia;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Emergencia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmergenciaRepository extends JpaRepository<Emergencia,Long> {
    
}
