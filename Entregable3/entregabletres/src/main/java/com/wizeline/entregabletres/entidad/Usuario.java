package com.wizeline.entregabletres.entidad;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UsuarioCollection")
public class Usuario {
    private String id;
    private String nombre;
    private String correo;
    private String password;
    private int edad;

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}
}
