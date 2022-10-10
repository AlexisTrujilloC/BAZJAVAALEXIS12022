package com.wizeline.entregabletres.servicio;

import com.wizeline.entregabletres.entidad.Broma;
import com.wizeline.entregabletres.entidad.ResultadoApiPublica;
import com.wizeline.entregabletres.otd.BromaOTD;
import com.wizeline.entregabletres.otd.RespuestaGeneralOTD;
import com.wizeline.entregabletres.otd.ResultadoApiPublicaOTD;
import com.wizeline.entregabletres.repositorio.ApiPublicaDAOImpl;
import com.wizeline.entregabletres.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;

@Service
public class ApiPublicaServiceImpl implements  ApiPublicaService{

    private static final Logger LOGGER = Logger.getLogger(ApiPublicaServiceImpl.class.getName());

    @Autowired
     ApiPublicaDAOImpl apiPublicaDAO;

    @Override
    public ResponseEntity<RespuestaGeneralOTD> consultaApiPublica(String lenguaje, String cantidad) {
        RespuestaGeneralOTD respuesta = new RespuestaGeneralOTD();

        LOGGER.info("Inicia Consulta hacia Api Publica...");
        ResultadoApiPublica resultadoApiPublica = apiPublicaDAO.consultaApiPublica(lenguaje, cantidad);
        if (Utils.isNullOrEmpty(resultadoApiPublica)){
            respuesta.setCodigo("ERR01");
            respuesta.setMensaje("Sin resultado al consumir API publica");
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
        ResultadoApiPublicaOTD resultadoApiPublicaOTD = new ResultadoApiPublicaOTD();
        BeanUtils.copyProperties(resultadoApiPublica,resultadoApiPublicaOTD);
        ArrayList<BromaOTD> bromasOTD = new ArrayList<>();

        for (Broma broma: resultadoApiPublica.getJokes() ) {
            BromaOTD bromaOTD= new BromaOTD();
            BeanUtils.copyProperties(broma , bromaOTD);
            bromasOTD.add(bromaOTD);
        }
        resultadoApiPublicaOTD.setJokes(bromasOTD);

        respuesta.setCodigo("OK0001");
        respuesta.setMensaje("Operacion exitosa");
        respuesta.setResultado(resultadoApiPublicaOTD);
        return new ResponseEntity<>(respuesta,HttpStatus.OK);
    }
}
