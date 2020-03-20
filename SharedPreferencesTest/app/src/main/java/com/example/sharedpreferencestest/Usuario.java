package com.example.sharedpreferencestest;

public class Usuario {
    private String nombre;
    private String correo;
    private byte[] Password;

    public Usuario(String nombre, String correo, byte[] password) {
        this.nombre = nombre;
        this.correo = correo;
        Password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public byte[] getPassword() {
        return Password;
    }

    public void setPassword(byte[] password) {
        Password = password;
    }


}
