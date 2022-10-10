package com.wizeline.entregabletres.servicio;

import com.wizeline.entregabletres.entidad.Usuario;
import com.wizeline.entregabletres.entidad.Usuarios;
import com.wizeline.entregabletres.otd.RespuestaGeneralOTD;
import com.wizeline.entregabletres.repositorio.UsuarioDAOImpl;
import com.wizeline.entregabletres.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioDAOImpl usuarioDAO;

    @Override
    public ResponseEntity<RespuestaGeneralOTD> obtenerUsuarios() {
        RespuestaGeneralOTD respuesta = new RespuestaGeneralOTD();
        Usuarios usuarios = usuarioDAO.obtenerUsuarios();
        if (Utils.isNullOrEmpty(usuarios)){
            respuesta.setCodigo("ERR01");
            respuesta.setMensaje("No se emcontraron usuarios");
            return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
        }
        respuesta.setCodigo("OK001");
        respuesta.setMensaje("Operacion exitosa");
        respuesta.setResultado(usuarios);
        return new ResponseEntity<>(respuesta,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RespuestaGeneralOTD> obtenerUsuario(String id) {
        RespuestaGeneralOTD respuesta = new RespuestaGeneralOTD();
        Usuario usuario = usuarioDAO.obtenerUsuario(id);
        if(Utils.isNullOrEmpty(usuario)){
            respuesta.setCodigo("ERR02");
            respuesta.setMensaje("No se encontró usuario para id: "+ id);
            return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
        }
        respuesta.setCodigo("OK002");
        respuesta.setMensaje("Operacion exitosa");
        respuesta.setResultado(usuario);
        return new ResponseEntity<>(respuesta,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RespuestaGeneralOTD> crearUsuario(Usuario body) {
        RespuestaGeneralOTD respuesta = new RespuestaGeneralOTD();
        Usuario usuario = usuarioDAO.crearUsuario(body);
        if (Utils.isNullOrEmpty(usuario)){
            respuesta.setCodigo("ERR03");
            respuesta.setMensaje("No se pudo crear el usuario");
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
        respuesta.setCodigo("OK003");
        respuesta.setMensaje("Operacion exitosa, se creo el usuario");
        respuesta.setResultado(usuario);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RespuestaGeneralOTD> eliminarUsuario(String id) {
        RespuestaGeneralOTD respuesta = new RespuestaGeneralOTD();
        long eliminados = usuarioDAO.eliminarUsuario(id);
        if (eliminados==0){
            respuesta.setCodigo("ERR04");
            respuesta.setMensaje("Error al eliminar, no se encontró el id: " + id);
            return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
        }
        respuesta.setCodigo("OK004");
        respuesta.setMensaje("Operacion exitosa, se elimino: " + eliminados + " usuario");
        return new ResponseEntity<>(respuesta,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RespuestaGeneralOTD> actualizarUsuario(String id, Usuario body) {
        RespuestaGeneralOTD respuesta = new RespuestaGeneralOTD();
        long modificados = usuarioDAO.actualizarUsuario(id, body);
        if (modificados==0){
            respuesta.setCodigo("ERR05");
            respuesta.setMensaje("No se pudo actualizar usuario");
            return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
        }
        respuesta.setCodigo("OK005");
        respuesta.setMensaje("Se actualizo correctamente: " +modificados);

        return new ResponseEntity<>(respuesta,HttpStatus.OK);
    }
}
