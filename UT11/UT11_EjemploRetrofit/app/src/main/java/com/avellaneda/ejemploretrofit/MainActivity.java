package com.avellaneda.ejemploretrofit;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;

import android.util.Log;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ListView;



import java.util.ArrayList;

import java.util.Iterator;



import retrofit2.Call;

import retrofit2.Callback;

import retrofit2.Response;

import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {



    ListView listaDatos;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        listaDatos=(ListView)findViewById(R.id.lstDatos);

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl(Servicio.BASE_URL)

                .addConverterFactory(GsonConverterFactory.create())

                .build();



        Servicio service = retrofit.create(Servicio.class);

        Call<Catalogo> requesCatalogo = service.listaCatalogo();



        requesCatalogo.enqueue(new Callback<Catalogo>() {

            @Override

            public void onResponse(Call<Catalogo> call, Response<Catalogo> response) {

                if (!response.isSuccessful()) {

                    Log.i("TAG", "Error" + response.code());

                } else {
                    Catalogo catalogo = response.body();
                    ArrayList<Curso>cursos = catalogo.getCourses();
                   /* cursos=new ArrayList<Curso>();

                    for (Iterator<Curso> iterator = catalogo.courses.iterator(); iterator.hasNext(); ) {

                        Curso curso = iterator.next();

                        cursos.add(curso);

                    }
*/


                    Adaptador miAdaptador=new Adaptador(getApplicationContext(), cursos);

                    listaDatos.setAdapter(miAdaptador);

                    listaDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override

                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        }

                    });

                }

            }



            @Override

            public void onFailure(Call<Catalogo> call, Throwable t) {

                 Log.e("tag", "Error: " + t.getMessage());

            }

        });

    }

}