package com.wizeline.entregabletres.repositorio;

import com.wizeline.entregabletres.entidad.Usuario;
import com.wizeline.entregabletres.entidad.Usuarios;

import java.util.List;

public interface UsuarioDAO {
    Usuarios obtenerUsuarios();
    Usuario crearUsuario(Usuario body);
    Usuario obtenerUsuario(String id);
    long eliminarUsuario(String id);
    long actualizarUsuario(String id, Usuario body);
}
