package com.example.mandaditos.Mandadero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("mandaderos/")
    Call<List<ListaMandaderos>> getMandaderos();
}
