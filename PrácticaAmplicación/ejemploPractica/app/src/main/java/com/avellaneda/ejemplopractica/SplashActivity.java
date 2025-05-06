package com.avellaneda.ejemplopractica;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import com.avellaneda.ejemplopractica.model.Cocktail;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class SplashActivity extends AppCompatActivity {
    private static final String API_URL = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic"; // Cambia la URL según tu API

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /**
         * Un Handler es una clase de Android que permite enviar y procesar mensajes o acciones (código) en un hilo específico.
         * Se utiliza frecuentemente para programar tareas que deben ejecutarse después de un tiempo o en el hilo principal (UI thread).
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Crear una cola de solicitudes
        RequestQueue queue = Volley.newRequestQueue(SplashActivity.this);

        // Hacer la solicitud GET a la API

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parsear la respuesta JSON
                            JSONArray drinksArray = response.getJSONArray("drinks");
                            List<Cocktail> cocktails = new ArrayList<>();

                            for (int i = 0; i < drinksArray.length(); i++) {
                                JSONObject drinkObject = drinksArray.getJSONObject(i);

                                // Obtener los datos del cóctel
                                String strDrink = drinkObject.getString("strDrink");
                                String strDrinkThumb = drinkObject.getString("strDrinkThumb");
                                String idDrink = drinkObject.getString("idDrink");

                                // Crear un objeto Cocktail
                                Cocktail cocktail = new Cocktail();
                                cocktail.setName(strDrink);
                                cocktail.setImageUrl(strDrinkThumb);
                                cocktail.setId(idDrink);

                                // Agregar el cóctel a la lista
                                cocktails.add(cocktail);
                            }

                            // Pasar la lista de cócteles a MainActivity
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            intent.putExtra("cocktail_list", (ArrayList<Cocktail>) cocktails);
                            startActivity(intent);
                            finish(); // Cerrar SplashActivity

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SplashActivity.this, "Error al parsear los datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SplashActivity.this, "Error en la solicitud", Toast.LENGTH_SHORT).show();
                    }
                });

        // Añadir la solicitud a la cola de solicitudes
        queue.add(jsonObjectRequest);
            }
        }, 2000); // 2000 ms = 2 segundos
        /*
        // Simulamos carga de datos
        ArrayList<Cocktail> cocktailList = loadCocktails();
        // sustituiremos por la llamada a la API

        // Lanzamos MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("cocktail_list", cocktailList);
        startActivity(intent);
        finish();*/
    }

    private ArrayList<Cocktail> loadCocktails() {
        // En el futuro aquí cargarás de JSON o API
        ArrayList<Cocktail> cocktails = new ArrayList<>();
        cocktails.add(new Cocktail("1", "Mojito", "https://yourimageurl.com/mojito.jpg"));
        cocktails.add(new Cocktail("2", "Piña Colada", "https://yourimageurl.com/pina_colada.jpg"));
        cocktails.add(new Cocktail("3", "Margarita", "https://yourimageurl.com/margarita.jpg"));
        return cocktails;
    }
}
