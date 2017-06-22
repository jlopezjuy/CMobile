package com.seguritech.cadmobile.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Emergencia.
 */
@Entity
@Table(name = "emergencia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Emergencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "telefono_movil")
    private String telefonoMovil;

    @Column(name = "id_corporacion")
    private String idCorporacion;

    @Column(name = "id_subtipo_emergencia")
    private String idSubtipoEmergencia;

    @Lob
    @Column(name = "imagen")
    private byte[] imagen;

    @Column(name = "imagen_content_type")
    private String imagenContentType;

    @Column(name = "latitud", precision=10, scale=2)
    private BigDecimal latitud;

    @Column(name = "longitud", precision=10, scale=2)
    private BigDecimal longitud;

    @ManyToOne
    private Ciudadano ciudadano;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public Emergencia telefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
        return this;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getIdCorporacion() {
        return idCorporacion;
    }

    public Emergencia idCorporacion(String idCorporacion) {
        this.idCorporacion = idCorporacion;
        return this;
    }

    public void setIdCorporacion(String idCorporacion) {
        this.idCorporacion = idCorporacion;
    }

    public String getIdSubtipoEmergencia() {
        return idSubtipoEmergencia;
    }

    public Emergencia idSubtipoEmergencia(String idSubtipoEmergencia) {
        this.idSubtipoEmergencia = idSubtipoEmergencia;
        return this;
    }

    public void setIdSubtipoEmergencia(String idSubtipoEmergencia) {
        this.idSubtipoEmergencia = idSubtipoEmergencia;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public Emergencia imagen(byte[] imagen) {
        this.imagen = imagen;
        return this;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getImagenContentType() {
        return imagenContentType;
    }

    public Emergencia imagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
        return this;
    }

    public void setImagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public Emergencia latitud(BigDecimal latitud) {
        this.latitud = latitud;
        return this;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public Emergencia longitud(BigDecimal longitud) {
        this.longitud = longitud;
        return this;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public Emergencia ciudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
        return this;
    }

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Emergencia emergencia = (Emergencia) o;
        if (emergencia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emergencia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Emergencia{" +
            "id=" + getId() +
            ", telefonoMovil='" + getTelefonoMovil() + "'" +
            ", idCorporacion='" + getIdCorporacion() + "'" +
            ", idSubtipoEmergencia='" + getIdSubtipoEmergencia() + "'" +
            ", imagen='" + getImagen() + "'" +
            ", imagenContentType='" + imagenContentType + "'" +
            ", latitud='" + getLatitud() + "'" +
            ", longitud='" + getLongitud() + "'" +
            "}";
    }
}
