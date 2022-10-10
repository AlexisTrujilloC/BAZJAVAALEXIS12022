package com.wizeline.entregabletres.controlador;

import com.wizeline.entregabletres.entidad.Usuario;
import com.wizeline.entregabletres.otd.RespuestaGeneralOTD;
import com.wizeline.entregabletres.servicio.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
public class UsuarioController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<RespuestaGeneralOTD> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<RespuestaGeneralOTD>  obtenerUsuario(
            @PathVariable String id
    ){
        return usuarioService.obtenerUsuario(id);
    }

    @PostMapping("/usuario")
    public ResponseEntity<RespuestaGeneralOTD> crearUsuario(
            @RequestBody Usuario body
            ){
        return usuarioService.crearUsuario(body);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<RespuestaGeneralOTD> eliminarUsuario(
            @PathVariable String id
    ){
        return usuarioService.eliminarUsuario(id);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<RespuestaGeneralOTD> actualizarUsuario(
            @PathVariable String id,
            @RequestBody Usuario body
    ){
        return usuarioService.actualizarUsuario(id,body);
    }

}
