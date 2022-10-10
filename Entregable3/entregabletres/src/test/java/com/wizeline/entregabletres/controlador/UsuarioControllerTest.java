package com.wizeline.entregabletres.controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wizeline.entregabletres.entidad.Usuario;
import com.wizeline.entregabletres.entidad.Usuarios;
import com.wizeline.entregabletres.otd.RespuestaGeneralOTD;
import com.wizeline.entregabletres.servicio.UsuarioServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.logging.Logger;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {
    private static final Logger LOGGER = Logger.getLogger(UsuarioControllerTest.class.getName());

    @MockBean
    UsuarioServiceImpl usuarioService;

    @Autowired
    UsuarioController usuarioController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //Happy Path
    @Test
    @DisplayName("Obtener Usuarios")
    public void obtenerUsuarios_RegresarUsuariosCodigo200() throws Exception {
        LOGGER.info("Se crea Respuesta del Service Mockeado");
        RespuestaGeneralOTD respuesta = new RespuestaGeneralOTD();
        Usuarios usuarios = new Usuarios();
        ArrayList<Usuario> usuarios1 = new ArrayList<>();
        Usuario usuario = new Usuario();
        usuario.setId("12345678");
        usuario.setNombre("Diego Alexis");
        usuario.setCorreo("diego@gmail.com");
        usuario.setPassword("passdiego");
        usuario.setEdad(27);
        usuarios1.add(usuario);
        usuarios.setUsuarios(usuarios1);
        respuesta.setCodigo("OK001");
        respuesta.setMensaje("Operacion exitosa");
        respuesta.setResultado(usuarios);
        ResponseEntity<RespuestaGeneralOTD> response = new ResponseEntity<>(respuesta, HttpStatus.OK);

        when(usuarioService.obtenerUsuarios()).thenReturn(response);
        LOGGER.info("Respuesta Service: " + objectMapper.writeValueAsString(respuesta));

        MvcResult result = mockMvc.perform(get("/v1/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("OK001"))
                .andExpect(jsonPath("$.mensaje").value("Operacion exitosa"))
                .andExpect(content().json(objectMapper.writeValueAsString(respuesta)))
                .andReturn();

        LOGGER.info("MvcResult Obtener Usuarios: " + result.getResponse().getContentAsString());

    }

    //Happy Path
    @Test
    @DisplayName("Obtener Usuario")
    public void obtenerUsuario_RegresarUsuarioCodigo200() throws Exception {
        LOGGER.info("Se crea Respuesta del Service Mockeado");
        RespuestaGeneralOTD respuesta = new RespuestaGeneralOTD();
        Usuario usuario = new Usuario();
        usuario.setId("12345678");
        usuario.setNombre("Diego Alexis");
        usuario.setCorreo("diego@gmail.com");
        usuario.setPassword("passdiego");
        usuario.setEdad(27);
        respuesta.setCodigo("OK002");
        respuesta.setMensaje("Operacion exitosa");
        respuesta.setResultado(usuario);
        ResponseEntity<RespuestaGeneralOTD> response = new ResponseEntity<>(respuesta, HttpStatus.OK);

        when(usuarioService.obtenerUsuario("12345678")).thenReturn(response);
        LOGGER.info("Respuesta Service: " + objectMapper.writeValueAsString(respuesta));

        MvcResult result = mockMvc.perform(get("/v1/api/usuario/12345678"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("OK002"))
                .andExpect(jsonPath("$.mensaje").value("Operacion exitosa"))
                .andExpect(content().json(objectMapper.writeValueAsString(respuesta)))
                .andReturn();

        LOGGER.info("MvcResult Obtener Usuario: " + result.getResponse().getContentAsString());
    }

    //Happy Path
    @Test
    @DisplayName("Crear Usuario")
    public void crearUsuario_RegresarUsuarioCodigo200() throws Exception {
        LOGGER.info("Se crea Respuesta del Service Mockeado");
        RespuestaGeneralOTD respuesta = new RespuestaGeneralOTD();
        Usuario usuario = new Usuario();
        usuario.setId("12345678");
        usuario.setNombre("Diego Alexis");
        usuario.setCorreo("diego@gmail.com");
        usuario.setPassword("passdiego");
        usuario.setEdad(27);

        respuesta.setCodigo("OK003");
        respuesta.setMensaje("Operacion exitosa, se creo el usuario");
        respuesta.setResultado(usuario);
        ResponseEntity<RespuestaGeneralOTD> response = new ResponseEntity<>(respuesta, HttpStatus.OK);

        LOGGER.info("Se crea Body de Request");
        Usuario usuarioBody = new Usuario();
        usuarioBody.setId("12345678");
        usuarioBody.setNombre("Diego Alexis");
        usuarioBody.setCorreo("diego@gmail.com");
        usuarioBody.setPassword("passdiego");
        usuarioBody.setEdad(27);

        when(usuarioService.crearUsuario(Mockito.any(Usuario.class))).thenReturn(response);
        LOGGER.info("Respuesta Service: " + objectMapper.writeValueAsString(respuesta));

        MvcResult result = mockMvc.perform(post("/v1/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioBody))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("OK003"))
                .andExpect(jsonPath("$.mensaje").value("Operacion exitosa, se creo el usuario"))
                .andExpect(content().json(objectMapper.writeValueAsString(respuesta)))
                .andReturn();

        LOGGER.info("MvcResult Crear Usuario: " + result.getResponse().getContentAsString());
    }

    //Happy Path
    @Test
    @DisplayName("Eliminar Usuarios")
    public void eliminarUsuario_Codigo200() throws Exception {
        LOGGER.info("Se crea Respuesta del Service Mockeado");
        RespuestaGeneralOTD respuesta = new RespuestaGeneralOTD();
        respuesta.setCodigo("OK004");
        respuesta.setMensaje("Operacion exitosa, se elimino: 1 usuario");
        ResponseEntity<RespuestaGeneralOTD> response = new ResponseEntity<>(respuesta, HttpStatus.OK);

        when(usuarioService.eliminarUsuario("12345678")).thenReturn(response);
        LOGGER.info("Respuesta Service: " + objectMapper.writeValueAsString(respuesta));

        MvcResult result = mockMvc.perform(delete("/v1/api/usuario/12345678"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("OK004"))
                .andExpect(jsonPath("$.mensaje").value("Operacion exitosa, se elimino: 1 usuario"))
                .andReturn();

        LOGGER.info("MvcResult Eliminar Usuario: " + result.getResponse().getContentAsString());

    }

    //Happy Path
    @Test
    @DisplayName("Actualizar Usuario")
    public void actualizarUsuario_Codigo200() throws Exception {
        LOGGER.info("Se crea Respuesta del Service Mockeado");
        RespuestaGeneralOTD respuesta = new RespuestaGeneralOTD();
        Usuario usuario = new Usuario();
        usuario.setId("12345678");
        usuario.setNombre("Diego Trujillo");
        usuario.setCorreo("alexis@gmail.com");
        usuario.setPassword("passdiego");
        usuario.setEdad(27);

        respuesta.setCodigo("OK005");
        respuesta.setMensaje("Se actualizo correctamente: 1");
        ResponseEntity<RespuestaGeneralOTD> response = new ResponseEntity<>(respuesta, HttpStatus.OK);

        when(usuarioService.actualizarUsuario(Mockito.eq("12345678"),Mockito.any(Usuario.class))).thenReturn(response);
        LOGGER.info("Respuesta Service: " + objectMapper.writeValueAsString(respuesta));

        MvcResult result = mockMvc.perform(put("/v1/api/usuario/12345678")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("OK005"))
                .andExpect(jsonPath("$.mensaje").value("Se actualizo correctamente: 1"))
                .andReturn();

        LOGGER.info("MvcResult Actualizar Usuario: " + result.getResponse().getContentAsString());

    }
}
