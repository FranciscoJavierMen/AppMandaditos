package com.example.mandaditos.Pedido;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ModeloPedidos {

    @SerializedName("description")
    private String descripcion;

    @SerializedName("date")
    private Date fecha;

    @SerializedName("ubication_origin")
    private String origen;

    @SerializedName("ubication_destiny")
    private String destino;

    @SerializedName("satae")
    private String estado;

    @SerializedName("cliente_id")
    private Integer idCliente;

    public ModeloPedidos(String descripcion, Date fecha, String origen, String destino, String estado, Integer idCliente) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    public ModeloPedidos(){}

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
}
