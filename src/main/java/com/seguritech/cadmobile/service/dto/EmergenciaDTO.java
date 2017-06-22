package com.seguritech.cadmobile.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Emergencia entity.
 */
public class EmergenciaDTO implements Serializable {

    private Long id;

    private String telefonoMovil;

    private String idCorporacion;

    private String idSubtipoEmergencia;

    @Lob
    private byte[] imagen;
    private String imagenContentType;

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

    public String getIdCorporacion() {
        return idCorporacion;
    }

    public void setIdCorporacion(String idCorporacion) {
        this.idCorporacion = idCorporacion;
    }

    public String getIdSubtipoEmergencia() {
        return idSubtipoEmergencia;
    }

    public void setIdSubtipoEmergencia(String idSubtipoEmergencia) {
        this.idSubtipoEmergencia = idSubtipoEmergencia;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getImagenContentType() {
        return imagenContentType;
    }

    public void setImagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
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

        EmergenciaDTO emergenciaDTO = (EmergenciaDTO) o;
        if(emergenciaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emergenciaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmergenciaDTO{" +
            "id=" + getId() +
            ", telefonoMovil='" + getTelefonoMovil() + "'" +
            ", idCorporacion='" + getIdCorporacion() + "'" +
            ", idSubtipoEmergencia='" + getIdSubtipoEmergencia() + "'" +
            ", imagen='" + getImagen() + "'" +
            ", latitud='" + getLatitud() + "'" +
            ", longitud='" + getLongitud() + "'" +
            "}";
    }
}
