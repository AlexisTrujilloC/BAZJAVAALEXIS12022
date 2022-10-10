package com.wizeline.entregabletres.controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wizeline.entregabletres.otd.BromaOTD;
import com.wizeline.entregabletres.otd.RespuestaGeneralOTD;
import com.wizeline.entregabletres.otd.ResultadoApiPublicaOTD;
import com.wizeline.entregabletres.servicio.ApiPublicaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.logging.Logger;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApiPublicaController.class)
public class ApiPublicaControllerTest {

    private static final Logger LOGGER = Logger.getLogger(ApiPublicaControllerTest.class.getName());

    @MockBean
    ApiPublicaServiceImpl apiPublicaService;

    @Autowired
    ApiPublicaController apiPublicaController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //HAPPY PATH
    @Test
    @DisplayName("Obtener bromas de API publica")
    public void dadoElParametroCantidad_RegresarCantidadDeBromasCodigo200() throws Exception {
        LOGGER.info("Se crea Respuesta del Service Mockeado");
        RespuestaGeneralOTD respuesta = new RespuestaGeneralOTD();
        ResultadoApiPublicaOTD resultadoApiPublicaOTD = new ResultadoApiPublicaOTD();
        ArrayList<BromaOTD> bromas = new ArrayList<>();
        BromaOTD broma1 = new BromaOTD();
        broma1.setCategory("categoria 1");
        broma1.setType("tipo 1");
        broma1.setJoke("broma 1");
        broma1.setId("id 1");
        broma1.setLang("lenguaje 1");
        bromas.add(broma1);
        resultadoApiPublicaOTD.setJokes(bromas);
        resultadoApiPublicaOTD.setAmount("1");
        respuesta.setCodigo("OK001");
        respuesta.setMensaje("Operacion Exitosa");
        respuesta.setResultado(bromas);
        ResponseEntity<RespuestaGeneralOTD> response = new ResponseEntity<>(respuesta, HttpStatus.OK);

        when(apiPublicaService.consultaApiPublica("es","1")).thenReturn(response);
        LOGGER.info("Respuesta Service: " + objectMapper.writeValueAsString(respuesta));

        MvcResult result = mockMvc.perform(get("/v1/api/publica/bromas?lenguaje=es&cantidad=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("OK001"))
                .andExpect(jsonPath("$.mensaje").value("Operacion Exitosa"))
                .andExpect(content().json(objectMapper.writeValueAsString(respuesta)))
                .andReturn();

        LOGGER.info("MvcResult Obtener bromas de API publica: " + result.getResponse().getContentAsString());
    }
}
