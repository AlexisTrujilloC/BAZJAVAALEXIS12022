package com.wizeline.entregabletres.servicio;

import com.wizeline.entregabletres.entidad.Broma;
import com.wizeline.entregabletres.entidad.ResultadoApiPublica;
import com.wizeline.entregabletres.entidad.Usuarios;
import com.wizeline.entregabletres.otd.BromaOTD;
import com.wizeline.entregabletres.otd.RespuestaGeneralOTD;
import com.wizeline.entregabletres.otd.ResultadoApiPublicaOTD;
import com.wizeline.entregabletres.repositorio.ApiPublicaDAOImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.logging.Logger;

import static org.mockito.Mockito.when;

@WebMvcTest(ApiPublicaServiceImpl.class)
public class ApiPublicaServiceImplTest {
    private static final Logger LOGGER = Logger.getLogger(ApiPublicaServiceImplTest.class.getName());

    @MockBean
    ApiPublicaDAOImpl apiPublicaDAO;

    @Autowired
    ApiPublicaServiceImpl apiPublicaService;

    @Test
    @DisplayName("Leer Bromas")
    public void read_Bromas(){

        LOGGER.info("Se crea Bromas esperadas por el DAO");

        ResultadoApiPublica resultadoApiPublica = new ResultadoApiPublica();
        ArrayList<Broma> bromas = new ArrayList<>();
        Broma broma1 = new Broma();
        Broma broma2 = new Broma();

        broma1.setCategory("categoria 1");
        broma1.setType("tipo 1");
        broma1.setJoke("broma 1");
        broma1.setId("id 1");
        broma1.setLang("lenguaje 1");

        broma2.setCategory("categoria 1");
        broma2.setType("tipo 1");
        broma2.setJoke("broma 1");
        broma2.setId("id 1");
        broma2.setLang("lenguaje 1");

        bromas.add(broma1);
        bromas.add(broma2);
        resultadoApiPublica.setJokes(bromas);
        resultadoApiPublica.setAmount("2");

        LOGGER.info("Se mockea respuesta DAO con valores esperados");
        when(apiPublicaDAO.consultaApiPublica(Mockito.eq("es"),Mockito.eq("2"))).thenReturn(resultadoApiPublica);

        LOGGER.info("Se inicia prueba con Service Api Publica");
        ResponseEntity<RespuestaGeneralOTD> respuesta = apiPublicaService.consultaApiPublica("es","2");

        LOGGER.info("Comparacion de resultados");
        ResultadoApiPublicaOTD resultadoApi = (ResultadoApiPublicaOTD) respuesta.getBody().getResultado();
        Assertions.assertAll(
                () -> Assertions.assertEquals("OK0001", respuesta.getBody().getCodigo()),
                () -> Assertions.assertEquals("2", resultadoApi.getAmount()),
                () -> Assertions.assertEquals(2, resultadoApi.getJokes().size())
        );

        LOGGER.info("Se realiza lectura Api Publica: " + respuesta.getStatusCode());
    }
}
