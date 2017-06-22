package com.seguritech.cadmobile.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Ciudadano.
 */
@Entity
@Table(name = "ciudadano")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ciudadano implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "paterno")
    private String paterno;

    @Column(name = "materno")
    private String materno;

    @Column(name = "email")
    private String email;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "telefono_movil")
    private String telefonoMovil;

    @Column(name = "telefono_fijo")
    private String telefonoFijo;

    @Column(name = "calle")
    private String calle;

    @Column(name = "numero")
    private String numero;

    @Column(name = "colonia")
    private String colonia;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "cp")
    private String cp;

    @Column(name = "genero")
    private String genero;

    @Column(name = "tipo_sanguineo")
    private String tipoSanguineo;

    @Column(name = "alergias")
    private String alergias;

    @Column(name = "padecimientos")
    private String padecimientos;

    @Column(name = "latitud", precision=10, scale=2)
    private BigDecimal latitud;

    @Column(name = "longitud", precision=10, scale=2)
    private BigDecimal longitud;

    @Column(name = "codigo_verfificacion")
    private String codigoVerificacion;

    @Column(name = "estado")
    private Boolean estado;

    @OneToMany(mappedBy = "ciudadano")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Boton> botons = new HashSet<>();

    @OneToMany(mappedBy = "ciudadano")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Emergencia> emeprgencias = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Ciudadano nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public Ciudadano paterno(String paterno) {
        this.paterno = paterno;
        return this;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public Ciudadano materno(String materno) {
        this.materno = materno;
        return this;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getEmail() {
        return email;
    }

    public Ciudadano email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Ciudadano fechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public Ciudadano telefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
        return this;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public Ciudadano telefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
        return this;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getCalle() {
        return calle;
    }

    public Ciudadano calle(String calle) {
        this.calle = calle;
        return this;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public Ciudadano numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public Ciudadano colonia(String colonia) {
        this.colonia = colonia;
        return this;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public Ciudadano municipio(String municipio) {
        this.municipio = municipio;
        return this;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCp() {
        return cp;
    }

    public Ciudadano cp(String cp) {
        this.cp = cp;
        return this;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getGenero() {
        return genero;
    }

    public Ciudadano genero(String genero) {
        this.genero = genero;
        return this;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public Ciudadano tipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
        return this;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getAlergias() {
        return alergias;
    }

    public Ciudadano alergias(String alergias) {
        this.alergias = alergias;
        return this;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getPadecimientos() {
        return padecimientos;
    }

    public Ciudadano padecimientos(String padecimientos) {
        this.padecimientos = padecimientos;
        return this;
    }

    public void setPadecimientos(String padecimientos) {
        this.padecimientos = padecimientos;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public Ciudadano latitud(BigDecimal latitud) {
        this.latitud = latitud;
        return this;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public Ciudadano longitud(BigDecimal longitud) {
        this.longitud = longitud;
        return this;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public Ciudadano codigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
        return this;
    }

    public void setCodigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    public Boolean isEstado() {
        return estado;
    }

    public Ciudadano estado(Boolean estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Set<Boton> getBotons() {
        return botons;
    }

    public Ciudadano botons(Set<Boton> botons) {
        this.botons = botons;
        return this;
    }

    public Ciudadano addBoton(Boton boton) {
        this.botons.add(boton);
        boton.setCiudadano(this);
        return this;
    }

    public Ciudadano removeBoton(Boton boton) {
        this.botons.remove(boton);
        boton.setCiudadano(null);
        return this;
    }

    public void setBotons(Set<Boton> botons) {
        this.botons = botons;
    }

    public Set<Emergencia> getEmeprgencias() {
        return emeprgencias;
    }

    public Ciudadano emeprgencias(Set<Emergencia> emergencias) {
        this.emeprgencias = emergencias;
        return this;
    }

    public Ciudadano addEmeprgencia(Emergencia emergencia) {
        this.emeprgencias.add(emergencia);
        emergencia.setCiudadano(this);
        return this;
    }

    public Ciudadano removeEmeprgencia(Emergencia emergencia) {
        this.emeprgencias.remove(emergencia);
        emergencia.setCiudadano(null);
        return this;
    }

    public void setEmeprgencias(Set<Emergencia> emergencias) {
        this.emeprgencias = emergencias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ciudadano ciudadano = (Ciudadano) o;
        if (ciudadano.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ciudadano.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ciudadano{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", paterno='" + getPaterno() + "'" +
            ", materno='" + getMaterno() + "'" +
            ", email='" + getEmail() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", telefonoMovil='" + getTelefonoMovil() + "'" +
            ", telefonoFijo='" + getTelefonoFijo() + "'" +
            ", calle='" + getCalle() + "'" +
            ", numero='" + getNumero() + "'" +
            ", colonia='" + getColonia() + "'" +
            ", municipio='" + getMunicipio() + "'" +
            ", cp='" + getCp() + "'" +
            ", genero='" + getGenero() + "'" +
            ", tipoSanguineo='" + getTipoSanguineo() + "'" +
            ", alergias='" + getAlergias() + "'" +
            ", padecimientos='" + getPadecimientos() + "'" +
            ", latitud='" + getLatitud() + "'" +
            ", longitud='" + getLongitud() + "'" +
            ", codigoVerificacion='" + getCodigoVerificacion() + "'" +
            ", estado='" + isEstado() + "'" +
            "}";
    }
}
