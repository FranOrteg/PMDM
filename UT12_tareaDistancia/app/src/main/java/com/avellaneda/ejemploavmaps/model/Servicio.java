package com.avellaneda.ejemploavmaps.model;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Servicio {


    // La URL base para Retrofit (esto es solo para configurar la URL base, no se coloca en el @GET)
    String BASE_URL = "https://raw.githubusercontent.com/nataliainformatica/PMDMdistancia/refs/heads/master/recursos/";

    // El método que obtiene los datos de la API usando el endpoint especificado
    @GET("city.list.json")
    Call<List<Localizacion>> obtenerLocalizaciones();
}
