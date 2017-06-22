package com.seguritech.cadmobile.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Boton.
 */
@Entity
@Table(name = "boton")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Boton implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "telefono_movil")
    private String telefonoMovil;

    @Column(name = "codigo_boton")
    private String codigoBoton;

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

    public Boton telefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
        return this;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getCodigoBoton() {
        return codigoBoton;
    }

    public Boton codigoBoton(String codigoBoton) {
        this.codigoBoton = codigoBoton;
        return this;
    }

    public void setCodigoBoton(String codigoBoton) {
        this.codigoBoton = codigoBoton;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public Boton latitud(BigDecimal latitud) {
        this.latitud = latitud;
        return this;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public Boton longitud(BigDecimal longitud) {
        this.longitud = longitud;
        return this;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public Boton ciudadano(Ciudadano ciudadano) {
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
        Boton boton = (Boton) o;
        if (boton.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), boton.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Boton{" +
            "id=" + getId() +
            ", telefonoMovil='" + getTelefonoMovil() + "'" +
            ", codigoBoton='" + getCodigoBoton() + "'" +
            ", latitud='" + getLatitud() + "'" +
            ", longitud='" + getLongitud() + "'" +
            "}";
    }
}
