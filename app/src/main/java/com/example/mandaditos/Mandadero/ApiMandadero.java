package com.example.mandaditos.Mandadero;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMandadero {

    public static String BASE_URL ="https://192.168.64.100:4000/api/";

    private static Retrofit retrofit;

    public static Retrofit getMandaderos(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
