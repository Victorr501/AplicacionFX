package com.aplicacion.aplicacionfx.modelo;

import javafx.beans.property.*;

import java.time.LocalDate;

public class UsuarioUI {
    //Parametros
    private StringProperty id;
    private StringProperty nombre;
    private StringProperty email;
    private StringProperty nombreUsuario;
    private StringProperty rol;
    private SimpleObjectProperty<LocalDate> fechaRegitro;
    private BooleanProperty activo;

    //Constructores
    public UsuarioUI(String id, String nombre, String email, String nombreUsuario, String rol, LocalDate fechaRegistro, Boolean activo){
        this.id = new SimpleStringProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.email = new SimpleStringProperty(email);
        this.nombreUsuario = new SimpleStringProperty(nombreUsuario);
        this.rol = new SimpleStringProperty(rol);
        this.fechaRegitro = new SimpleObjectProperty<>(fechaRegistro);
        this.activo = new SimpleBooleanProperty(activo);
    }

    //Getters
    public String getId(){return id.get();}
    public String getNombre(){return nombre.get();}
    public String getEmail(){return email.get();}
    public String getNombreUsuario(){return nombreUsuario.get();}
    public String getRol(){return rol.get();}
    public LocalDate getFechaRegistro(){return fechaRegitro.get();}
    public Boolean getActivo(){return activo.get();}

    //Setters
    public void setId(String id) {
        this.id.set(id);
    }
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    public void setEmail(String email) {
        this.email.set(email);
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario.set(nombreUsuario);
    }
    public void setRol(String rol) {
        this.rol.set(rol);
    }
    public void setActivo(boolean activo) {
        this.activo.set(activo);
    }
    public void setFechaRegitro(LocalDate fechaRegitro) {
        this.fechaRegitro.set(fechaRegitro);
    }
    //Getter para la property
    public StringProperty Id() {
        return id;
    }
    public StringProperty Nombre() {
        return nombre;
    }
    public StringProperty Email() {
        return email;
    }
    public StringProperty NombreUsuario() {
        return nombreUsuario;
    }
    public StringProperty Rol() {
        return rol;
    }
    public SimpleObjectProperty<LocalDate> FechaRegitro() {
        return fechaRegitro;
    }
    public BooleanProperty Activo() {
        return activo;
    }

    @Override
    public String toString(){
        return "UsuarioUI{" +
                "id='" + id.get() + '\'' +
                ", nombre='" + nombre.get() + '\'' +
                ", email='" + email.get() + '\'' +
                ", nombreUsuario='" + nombreUsuario.get() + '\'' +
                ", rol='" + rol.get() + '\'' +
                ", activo=" + activo.get() +
                '}';
    }
}
