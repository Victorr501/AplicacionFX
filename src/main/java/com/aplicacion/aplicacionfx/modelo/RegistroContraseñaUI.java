package com.aplicacion.aplicacionfx.modelo;

public class RegistroContraseñaUI {
    private String passwor;
    private String gmail;

    public RegistroContraseñaUI(String gmail){
        this.gmail = gmail;
    }

    public RegistroContraseñaUI(String gmail, String passwor){
        this.gmail = gmail;
        this.passwor = passwor;
    }

    public String getPasswor() {
        return passwor;
    }

    public void setPasswor(String passwor) {
        this.passwor = passwor;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
