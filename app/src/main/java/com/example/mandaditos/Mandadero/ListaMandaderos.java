package com.example.mandaditos.Mandadero;

import com.google.gson.annotations.SerializedName;

public class ListaMandaderos {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String nombre;

    @SerializedName("status")
    private String estado;



    public ListaMandaderos(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public ListaMandaderos(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
