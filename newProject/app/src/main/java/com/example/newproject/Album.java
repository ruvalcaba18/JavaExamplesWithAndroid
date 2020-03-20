package com.example.newproject;

public class Album {
    private String Nombre;
    private int numOfSongs;
    private int thumbNail;

    public Album(){

    }

    public Album(String nombre, int numOfSongs, int thumbNail) {
        Nombre = nombre;
        this.numOfSongs = numOfSongs;
        this.thumbNail = thumbNail;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public int getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(int thumbNail) {
        this.thumbNail = thumbNail;
    }


}
