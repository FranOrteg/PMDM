package com.avellaneda.ejemploavmaps;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.avellaneda.ejemploavmaps.model.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class SplashActivity extends AppCompatActivity {
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar = findViewById(R.id.progressBar);
        // Vamos a hacer la conexión a la API desde la pantalla de Splash
        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Servicio.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicio apiService = retrofit.create(Servicio.class);

        // Hacer la llamada
        Call<List<Localizacion>> call = apiService.obtenerLocalizaciones();

        call.enqueue(new Callback<List<Localizacion>>() {
            @Override
            public void onResponse(Call<List<Localizacion>> call, Response<List<Localizacion>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Localizacion> localizacions = (ArrayList<Localizacion>) response.body();


                    // Pasamos los datos serializados al MainActivity
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("array",  localizacions);
                    startActivity(intent);
                    finish();
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<List<Localizacion>> call, Throwable t) {
                showError();
            }
        });
    }

    private void showError() {
        Toast.makeText(this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
        // Podrías reintentar, cerrar la app, o continuar sin datos
    }
}
