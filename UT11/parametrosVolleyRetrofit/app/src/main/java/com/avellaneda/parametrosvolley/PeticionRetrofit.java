package com.avellaneda.parametrosvolley;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import com.avellaneda.parametrosvolley.modelR.EmojiApiService;
import com.avellaneda.parametrosvolley.modelR.EmojiResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeticionRetrofit extends AppCompatActivity {
    private static final String BASE_URL = "https://api.api-ninjas.com/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peticion_retrofit);

        // Crear instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())  // Convertir JSON automáticamente
                .build();

        // Crear instancia de la API
        EmojiApiService apiService = retrofit.create(EmojiApiService.class);

        // Hacer la llamada a la API
        Call<List<EmojiResponse>> call = apiService.getEmoji("slightly smiling face");

        call.enqueue(new Callback<List<EmojiResponse>>() {
            @Override
            public void onResponse(Call<List<EmojiResponse>> call, Response<List<EmojiResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<EmojiResponse> emojis = response.body();
                    for (EmojiResponse emoji : emojis) {
                        Log.d("Retrofit", "Emoji: " + emoji.getCharacter() + ", Nombre: " + emoji.getName());
                    }
                } else {
                    Log.e("Retrofit", "Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<EmojiResponse>> call, Throwable t) {
                Log.e("Retrofit", "Error en la petición: " + t.getMessage());
            }
        });
    }
}
