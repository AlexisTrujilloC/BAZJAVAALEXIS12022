package com.wizeline.entregabletres.repositorio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wizeline.entregabletres.entidad.ResultadoApiPublica;
import com.wizeline.entregabletres.enums.Lenguaje;
import com.wizeline.entregabletres.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.logging.Logger;

@Component
public class ApiPublicaDAOImpl implements ApiPublicaDAO {

    private static final Logger LOGGER = Logger.getLogger(ApiPublicaDAOImpl.class.getName());

    @Value("${spring.external.service.base-url}")
    private String urlPublica;

    @Override
    public ResultadoApiPublica consultaApiPublica(String lenguaje, String cantidad){
        ResultadoApiPublica resultado = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(urlPublica)
                    .queryParam("lang",Utils.getLenguaje(Lenguaje.SPANISH))
                    .queryParam("amount",cantidad).build();
            LOGGER.info("Se consulta API publica: " + builder.toUriString());
            RestTemplate restTemplate = new RestTemplate();
            String json = restTemplate.getForObject(builder.toUriString(),String.class);//RestTemplate
            if (Utils.isNullOrEmpty(json)) {
                LOGGER.info("No se encontró respuesta de la API");
                return null;
            }

            resultado = objectMapper.readValue(json,ResultadoApiPublica.class);//jackson
            LOGGER.info("Resultado API publica: "+ resultado.toString());
        } catch (JsonProcessingException e) {
            LOGGER.info("No se puede mapear objeto: " + e.getMessage());
        }
        return resultado;
    }

}
