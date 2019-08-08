package com.example.mandaditos.Services;

import com.example.mandaditos.Mandadero.ModeloMandaderos;
import com.example.mandaditos.Pedido.ModeloPedidos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    //Lista completa de mandaderos
    @GET("mandaderos")
    Call<List<ModeloMandaderos>> getMandaderos();

    //Lista completa d epedidos de un cliente
    @GET("clientes/7/pedidos")
    Call<List<ModeloPedidos>> getpedidos();
}
