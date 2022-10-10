package com.wizeline.entregabletres.servicio;

import com.wizeline.entregabletres.otd.RespuestaGeneralOTD;
import org.springframework.http.ResponseEntity;

public interface ApiPublicaService {

    ResponseEntity<RespuestaGeneralOTD> consultaApiPublica(String lenguaje, String cantidad);

}
