package com.avellaneda.parametrosvolley;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
       enviarPeticion();

        //enviarPeticion2();
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),PeticionRetrofit.class);
                startActivity(i);
            }
        });
    }

    private void enviarPeticion(){
      //  https://api.api-ninjas.com/v1/emoji?name=slightly smiling face
        String baseUrl = "https://api.api-ninjas.com/v1/emoji";
        String parametro1 = "name";
        try {
            // Construir la URL con parámetros dinámicos
            String finalUrl = baseUrl + "?name=" + URLEncoder.encode(parametro1, "UTF-8");
            /*IMPORTANTE - LOS ESPACIOS DEBEN CODIFICARSE CORRECTAMENTE
            URLEncoder.encode(parametro2, "UTF-8") convierte "slightly smiling face" en "slightly+smiling+face",
            que  es la forma correcta de escribirlo en una URL.
 */
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, finalUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            String valor = response.getJSONObject(0).getString("code"); // Acceder a los datos del JSON
                            Log.d("Response",valor);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-Api-Key", "tuAPIaqui");  // API Key en headers
                return headers;
            }
        };

        // Agregar a la cola de solicitudes
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    }


    private void enviarPeticion2(){
        //  https://api.api-ninjas.com/v1/emoji?name=slightly smiling face
        String baseUrl = "https://api.api-ninjas.com/v1/emoji";
        String parametro1 = "name";
        // Construir la URL con parámetros dinámicos
        try {
            String url = baseUrl + "?name=" + URLEncoder.encode(parametro1, "UTF-8");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // Manejar la respuesta aquí
                    Log.d("Response", response);
                },
                error -> {
                    // Manejar el error aquí
                    Log.e("Volley Error", error.toString());
                }) {
            @Override
            public Map<String, String> getHeaders()  {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-Api-Key", "tuKEYaqui");  // API Key en headers
                return headers;
            }
        };


// Agregar la solicitud a la cola de Volley
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}