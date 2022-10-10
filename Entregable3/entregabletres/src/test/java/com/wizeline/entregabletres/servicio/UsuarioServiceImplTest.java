package com.wizeline.entregabletres.servicio;

import com.wizeline.entregabletres.entidad.Usuario;
import com.wizeline.entregabletres.entidad.Usuarios;
import com.wizeline.entregabletres.otd.RespuestaGeneralOTD;
import com.wizeline.entregabletres.repositorio.UsuarioDAOImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.mockito.Mockito.when;

@WebMvcTest(UsuarioServiceImpl.class)
public class UsuarioServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(UsuarioServiceImplTest.class.getName());

    @MockBean
    UsuarioDAOImpl usuarioDAO;

    @Autowired
    UsuarioServiceImpl usuarioService;

    //================  Pruebas unitarias CRUD ======================
    //---------- Lectura---------------------
    @Test
    @DisplayName("Leer Usuarios")
    public void read_Usuarios(){
        LOGGER.info("Se crean valores esperados");
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        usuario1.setId("123456");
        usuario1.setNombre("Diego Alexis");
        usuario1.setCorreo("diego@gmail.com");
        usuario1.setPassword("passDiego");
        usuario1.setEdad(27);

        usuario2.setId("654321");
        usuario2.setNombre("Marco Antonio");
        usuario2.setCorreo("marco@gmail.com");
        usuario2.setPassword("passMarco");
        usuario2.setEdad(21);

        usuarios.add(usuario1);
        usuarios.add(usuario2);
        Usuarios usuariosDTO = new Usuarios();
        usuariosDTO.setUsuarios(new ArrayList<>(usuarios));
        LOGGER.info("Usuarios totales esperados: "+usuarios.size());
        LOGGER.info("Se mockea respuesta DAO con valores esperados");
        when(usuarioDAO.obtenerUsuarios()).thenReturn(usuariosDTO);

        LOGGER.info("Se inicia prueba con Service");
        ResponseEntity<RespuestaGeneralOTD> respuesta = usuarioService.obtenerUsuarios();

        LOGGER.info("Comparacion de resultados");
        Usuarios usuariosResponse = (Usuarios) respuesta.getBody().getResultado();
        Assertions.assertAll(
                () -> Assertions.assertEquals("OK001", respuesta.getBody().getCodigo()),
                () -> Assertions.assertEquals(usuarios.size(), usuariosResponse.getUsuarios().size())
        );

        LOGGER.info("Se realiza lectura exitosa: " + respuesta.getStatusCode());
    }

    @Test
    @DisplayName("Leer Usuario por id")
    public void read_Usuario(){
        LOGGER.info("Se crea usuario esperado");
        Usuario usuario = new Usuario();
        usuario.setId("123456");
        usuario.setNombre("Diego Alexis");
        usuario.setCorreo("diego@gmail.com");
        usuario.setPassword("passDiego");
        usuario.setEdad(27);

        LOGGER.info("Id de usuario: "+ usuario.getId());
        LOGGER.info("Se mockea respuesta DAO con valor esperado");
        when(usuarioDAO.obtenerUsuario("123456")).thenReturn(usuario);

        LOGGER.info("Se inicia prueba con Service");
        ResponseEntity<RespuestaGeneralOTD> respuesta = usuarioService.obtenerUsuario("123456");

        LOGGER.info("Comparacion de resultados");
        Usuario usuarioResponse = (Usuario) respuesta.getBody().getResultado();
        Assertions.assertAll(
                () -> Assertions.assertEquals("OK002", respuesta.getBody().getCodigo()),
                () -> Assertions.assertEquals("123456", usuarioResponse.getId())
        );

        LOGGER.info("Se realiza lectura exitosa: " + respuesta.getStatusCode());
    }

    //------------- Creacion------------------
    @Test
    @DisplayName("Crear Usuario")
    public void create_Usuario(){
        LOGGER.info("Se crea usuario a guardar");
        Usuario usuario = new Usuario();
        usuario.setId("123456");
        usuario.setNombre("Diego Alexis");
        usuario.setCorreo("diego@gmail.com");
        usuario.setPassword("passDiego");
        usuario.setEdad(27);

        LOGGER.info("Se mockea respuesta DAO con valor esperado");
        when(usuarioDAO.crearUsuario(Mockito.any(Usuario.class))).thenReturn(usuario);

        LOGGER.info("Se inicia prueba con Service");
        ResponseEntity<RespuestaGeneralOTD> respuesta = usuarioService.crearUsuario(usuario);

        LOGGER.info("Comparacion de resultados");
        Usuario usuarioResponse = (Usuario) respuesta.getBody().getResultado();
        Assertions.assertAll(
                () -> Assertions.assertEquals("OK003", respuesta.getBody().getCodigo()),
                () -> Assertions.assertEquals(usuario.getId(), usuarioResponse.getId())
        );

        LOGGER.info("Se realiza creacion exitosa: " + respuesta.getStatusCode());

    }

    //-------------- Actualizacion ---------------

    @Test
    @DisplayName("Actualizar Usuario")
    public void update_Usuario(){
        LOGGER.info("Se crea Usuario ejemplo a actualizar");
        Usuario usuario = new Usuario();
        usuario.setNombre("Diego Alexis");
        usuario.setCorreo("diego@gmail.com");
        usuario.setPassword("passDiego");
        usuario.setEdad(27);

        LOGGER.info("Se mockea respuesta DAO con valor esperado");
        when(usuarioDAO.actualizarUsuario(Mockito.eq("123456"),Mockito.any(Usuario.class))).thenReturn(1L);

        LOGGER.info("Se inicia prueba con Service");
        ResponseEntity<RespuestaGeneralOTD> respuesta = usuarioService.actualizarUsuario("123456",usuario);

        LOGGER.info("Comparacion de resultados");
        Assertions.assertAll(
                () -> Assertions.assertEquals("OK005", respuesta.getBody().getCodigo()),
                () -> Assertions.assertEquals("Se actualizo correctamente: 1", respuesta.getBody().getMensaje())
        );

        LOGGER.info("Se realiza creacion exitosa: " + respuesta.getStatusCode());

    }

    //-------------- Eliminacion -----------------
    @Test
    @DisplayName("Eliminar Usuario")
    public void delete_Usuario(){

        LOGGER.info("Se mockea respuesta DAO con valor esperado");
        when(usuarioDAO.eliminarUsuario(Mockito.eq("123456"))).thenReturn(1L);

        LOGGER.info("Se inicia prueba con Service Eliminacion");
        ResponseEntity<RespuestaGeneralOTD> respuesta = usuarioService.eliminarUsuario("123456");

        LOGGER.info("Comparacion de resultados");
        Assertions.assertAll(
                () -> Assertions.assertEquals("OK004", respuesta.getBody().getCodigo()),
                () -> Assertions.assertEquals("Operacion exitosa, se elimino: 1 usuario", respuesta.getBody().getMensaje())
        );

        LOGGER.info("Se realiza eliminacion exitosa: " + respuesta.getStatusCode());
    }

}
