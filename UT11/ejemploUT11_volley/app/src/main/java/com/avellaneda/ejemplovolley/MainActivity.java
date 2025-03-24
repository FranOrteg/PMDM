package com.avellaneda.ejemplovolley;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import android.util.Log;


import android.widget.ArrayAdapter;
import android.widget.TextView;

import android.widget.Toast;



import com.android.volley.Request;

import com.android.volley.RequestQueue;

import com.android.volley.Response;

import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;
import com.avellaneda.ejemplovolley.model.DatosCurso;


import org.json.JSONArray;

import org.json.JSONException;

import org.json.JSONObject;



import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {



    // private static final String URL = "https://www.udacity.com/public-api/v0/courses";

    private static final String URL = "https://raw.githubusercontent.com/nataliainformatica/ejercicios_clase_PR/refs/heads/main/Recursos/ficheroCursos.json"; //Si hacemos una copia del fichero en un servidor web local
    // sustituye la ip por la de tu servidor

   //private ListView listaDatos;
    ArrayList<DatosCurso> lista;
    TextView tv1;
    String cad = "";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.tv1);
        lista = new ArrayList<DatosCurso>();

        ArrayAdapter<String > miadaptador;
        RequestQueue request = Volley.newRequestQueue(this);
        //Como el elemento raiz en este caso (Viendo el fichero JSON) es un objeto (no un array)
        // instanciamos un jsonObjectRequest (si fuera un array instanciariamos un JsonArrayRequest)
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Cadena para hacer pruebas con Toast
                 cad = "";
                try {
                    Log.d("RESPONSE", response.toString(0));

                   // JSONObject jsonObjectPrincipal = new JSONObject(response.toString(0));
                    JSONObject jsonObjectPrincipal = response;
                    JSONArray JSONList = jsonObjectPrincipal.getJSONArray("courses");
                    Log.d("TAMAÑO", String.valueOf(JSONList.length())); //Compruebo que accedo a los cursos viendo la cantidad de ellos
                    Toast.makeText(getApplicationContext(), ""+JSONList.length(), Toast.LENGTH_LONG).show();
                    //Saco todas los cursos de uno en uno
                    for (int i = 0; i < JSONList.length(); i++) {
                        //JSONObject objetoJson = JSONList.getJSONObject(i);
                        String clave = JSONList.getJSONObject(i).getString("key");
                        cad+="CURSO Nº "+ (i+1)+"\nClave: "+ clave;
                        String titulo = JSONList.getJSONObject(i).getString("title");
                        cad+="\nTitulo: "+ titulo;
                        String subtitulo = JSONList.getJSONObject(i).getString("subtitle");
                        cad+="\nSubtitulo: "+ subtitulo;
                        ArrayList<String> instructores;
                        instructores=new ArrayList<String>();
                        JSONArray arrayInstructores = JSONList.getJSONObject(i).getJSONArray("instructors");
                        cad+="\nInstructores: ";

                        if(arrayInstructores.length()==0)
                            cad+="NO ESPECIFICADO";
                        for(int j=0; j<arrayInstructores.length(); j++) {
                            instructores.add(arrayInstructores.getJSONObject(j).getString("name"));
                            cad+="\n\t\t"+arrayInstructores.getJSONObject(j).getString("name");

                        }
                        String paginaWeb = JSONList.getJSONObject(i).getString("homepage");
                        cad+="\nWeb: "+ paginaWeb+"\n\n";
                        DatosCurso registro = new DatosCurso(clave, titulo, subtitulo, paginaWeb);
                         //Con la linea siguiente cargo el ArrayList de forma que ya podré intentar mostrar todos los datos a través de un ListView
                        lista.add(registro);



                    } // TERMINA EL FOR

                    //Toast.makeText(getApplicationContext(), cad, Toast.LENGTH_LONG).show();
                    // MOSTRAMOS LA INFORMACIÓN EN LA UI
                    tv1.setText(cad);


                    // aquí
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              Log.d("SERVER" , "Error VOLLEY");
            }

        });

        request.add(jsonObjectRequest);
        tv1.setText(cad);

    }

}