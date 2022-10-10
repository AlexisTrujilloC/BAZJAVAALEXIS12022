package com.wizeline.entregabletres.repositorio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wizeline.entregabletres.entidad.ResultadoApiPublica;

public interface ApiPublicaDAO {
    ResultadoApiPublica consultaApiPublica(String lenguaje, String cantidad);
}
