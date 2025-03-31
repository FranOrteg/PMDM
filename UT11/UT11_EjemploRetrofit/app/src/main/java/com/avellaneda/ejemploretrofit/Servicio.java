package com.avellaneda.ejemploretrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Servicio {
    public static final String BASE_URL ="https://raw.githubusercontent.com/nataliainformatica/ejercicios_clase_PR/refs/heads/main/Recursos/"; // ip del servidor local
    @GET("ficheroCursos.json")
    Call<Catalogo> listaCatalogo();

}