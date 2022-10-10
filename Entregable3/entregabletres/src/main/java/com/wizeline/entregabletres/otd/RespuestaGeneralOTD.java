package com.wizeline.entregabletres.otd;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespuestaGeneralOTD {
    private String codigo;
    private String mensaje;
    private Object resultado;

    public String getCodigo() {return codigo;}
    public void setCodigo(String codigo) {this.codigo = codigo;}
    public String getMensaje() {return mensaje;}
    public void setMensaje(String mensaje) {this.mensaje = mensaje;}
    public Object getResultado() {return resultado;}
    public void setResultado(Object resultado) {this.resultado = resultado;}
}
