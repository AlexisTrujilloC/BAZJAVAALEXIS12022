package com.wizeline.entregabletres.repositorio;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.wizeline.entregabletres.entidad.Usuario;
import com.wizeline.entregabletres.entidad.Usuarios;
import com.wizeline.entregabletres.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioDAOImpl implements UsuarioDAO{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Usuarios obtenerUsuarios() {
        List<Usuario> usuariosMongoDB = mongoTemplate.findAll(Usuario.class);
        if (Utils.isNullOrEmpty(usuariosMongoDB))
            return null;
        Usuarios usuarios = new Usuarios();
        usuarios.setUsuarios(new ArrayList<>(usuariosMongoDB));
        return usuarios;
    }

    @Override
    public Usuario crearUsuario(Usuario body) {
        Usuario usuario = mongoTemplate.save(body);
        return usuario;
    }

    @Override
    public Usuario obtenerUsuario(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        Usuario usuario = mongoTemplate.findOne(query,Usuario.class);
        return usuario;
    }

    @Override
    public long eliminarUsuario(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        DeleteResult deleteResult = mongoTemplate.remove(query,Usuario.class);
        return deleteResult.getDeletedCount();
    }

    @Override
    public long actualizarUsuario(String id, Usuario body) {
        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("nombre",body.getNombre());
        update.set("correo",body.getCorreo());
        update.set("password",body.getPassword());
        update.set("edad",body.getEdad());
        UpdateResult updateResult = mongoTemplate.updateFirst(query,update,Usuario.class);

        return updateResult.getModifiedCount();
    }


}
