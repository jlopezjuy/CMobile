package com.seguritech.cadmobile.service.dto;

/**
 * @author jlopez
 */
public class VerificacionDTO {
    private String codigo;
    private String mensaje;

    public VerificacionDTO() {
    }

    public VerificacionDTO(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
