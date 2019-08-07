package com.example.mandaditos.Mandadero;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMandadero {

    public static String BASE_URL ="http://192.168.1.79:4000/api/";

    private static Retrofit retrofit;

    public static Retrofit getMandaderos(){
        if (retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
