package com.wizeline.entregabletres.controlador;

import com.wizeline.entregabletres.otd.RespuestaGeneralOTD;
import com.wizeline.entregabletres.servicio.ApiPublicaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/publica")
public class ApiPublicaController {

    @Autowired
    ApiPublicaServiceImpl apiPublicaService;

    @GetMapping("/bromas")
    public ResponseEntity<RespuestaGeneralOTD> consultaApiPublica(
            @RequestParam("lenguaje") String lenguaje,
            @RequestParam("cantidad") String cantidad
    ){
        return apiPublicaService.consultaApiPublica(lenguaje, cantidad);
    }
}
