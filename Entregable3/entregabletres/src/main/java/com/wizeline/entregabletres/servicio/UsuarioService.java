package com.wizeline.entregabletres.servicio;

import com.wizeline.entregabletres.entidad.Usuario;
import com.wizeline.entregabletres.otd.RespuestaGeneralOTD;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {
    ResponseEntity<RespuestaGeneralOTD> obtenerUsuarios();
    ResponseEntity<RespuestaGeneralOTD> obtenerUsuario(String id);
    ResponseEntity<RespuestaGeneralOTD> crearUsuario(Usuario body);
    ResponseEntity<RespuestaGeneralOTD> eliminarUsuario(String id);
    ResponseEntity<RespuestaGeneralOTD> actualizarUsuario(String id, Usuario body);
}
