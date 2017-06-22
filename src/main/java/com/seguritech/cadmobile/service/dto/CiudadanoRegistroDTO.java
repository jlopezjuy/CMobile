package com.seguritech.cadmobile.service.dto;

/**
 * @author jlopez
 */
public class CiudadanoRegistroDTO {
    private Long codigo;
    private String mensaje;
    private Long idCiudadano;

    public CiudadanoRegistroDTO() {
    }

    public CiudadanoRegistroDTO(Long codigo, String mensaje, Long idCiudadano) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.idCiudadano = idCiudadano;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getIdCiudadano() {
        return idCiudadano;
    }

    public void setIdCiudadano(Long idCiudadano) {
        this.idCiudadano = idCiudadano;
    }
}
