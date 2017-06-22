package com.seguritech.cadmobile.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Boton entity.
 */
public class BotonDTO implements Serializable {

    private Long id;

    private String telefonoMovil;

    private String codigoBoton;

    private BigDecimal latitud;

    private BigDecimal longitud;

    private Long ciudadanoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getCodigoBoton() {
        return codigoBoton;
    }

    public void setCodigoBoton(String codigoBoton) {
        this.codigoBoton = codigoBoton;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public Long getCiudadanoId() {
        return ciudadanoId;
    }

    public void setCiudadanoId(Long ciudadanoId) {
        this.ciudadanoId = ciudadanoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BotonDTO botonDTO = (BotonDTO) o;
        if(botonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), botonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BotonDTO{" +
            "id=" + getId() +
            ", telefonoMovil='" + getTelefonoMovil() + "'" +
            ", codigoBoton='" + getCodigoBoton() + "'" +
            ", latitud='" + getLatitud() + "'" +
            ", longitud='" + getLongitud() + "'" +
            "}";
    }
}
