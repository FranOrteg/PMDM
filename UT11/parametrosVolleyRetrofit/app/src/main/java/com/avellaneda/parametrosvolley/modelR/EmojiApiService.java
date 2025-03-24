package com.avellaneda.parametrosvolley.modelR;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

// Definir la interfaz para la API
public interface EmojiApiService {

    // con Retrofit
    // es más sencillo añadir la KEY como cabecera en la petición
    @Headers("X-Api-Key: tuKEYaqui")  // Agregar la API Key
    @GET("emoji")
    Call<List<EmojiResponse>> getEmoji(@Query("name") String name);


}
