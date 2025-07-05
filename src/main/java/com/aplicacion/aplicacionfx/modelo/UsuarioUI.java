package com.aplicacion.aplicacionfx.modelo;

import javafx.beans.property.*;

public class UsuarioUI {
    //Parametros
    private StringProperty id;
    private StringProperty nombre;
    private StringProperty email;

    private StringProperty passwordPlana;
    private StringProperty rol;
    private BooleanProperty activo;

    //Constructores


    public UsuarioUI() {
    }

    //Este se utiliza para el nomral, quiere decir que no tiene contraseña
    public UsuarioUI(String id, String nombre, String email, String rol, Boolean activo){
        this.id = new SimpleStringProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.email = new SimpleStringProperty(email);
        this.rol = new SimpleStringProperty(rol);
        this.activo = new SimpleBooleanProperty(activo);
    }

    //Este se utiliza para regristrar
    public UsuarioUI(String id, String nombre, String email,String passwrodHash, String rol, Boolean activo){
        this.id = new SimpleStringProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.email = new SimpleStringProperty(email);
        this.passwordPlana = new SimpleStringProperty(passwrodHash);
        this.rol = new SimpleStringProperty(rol);
        this.activo = new SimpleBooleanProperty(activo);
    }



    //Getters
    public String getId(){return id.get();}
    public String getNombre(){return nombre.get();}
    public String getEmail(){return email.get();}
    public String getPasswordPlana() { return passwordPlana.get(); }
    public String getRol(){return rol.get();}
    public Boolean isActivo(){return activo.get();}

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
    public void setPasswordPlana(String passwordPlana) { this.passwordPlana.set(passwordPlana); }
    public void setRol(String rol) {
        this.rol.set(rol);
    }
    public void setActivo(boolean activo) {
        this.activo.set(activo);
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
    public StringProperty passwordPlanaProperty() { return passwordPlana; }
    public StringProperty Rol() {
        return rol;
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
                ", rol='" + rol.get() + '\'' +
                ", activo=" + activo.get() +
                '}';
    }
}
