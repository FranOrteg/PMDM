package com.avellaneda.ejemplolistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.avellaneda.ejemplolistview.model.ModeloPersona;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class EntradaDatos extends AppCompatActivity {


    private ImageView imageView;
    private Button btn,botnVolver;

    private int contador;

    private EditText edt1, edt2;
    private  int numeroAleatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_datos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        imageView = findViewById(R.id.imageView);
        btn = findViewById(R.id.btn);
        botnVolver = findViewById(R.id.btnVOLVER);
        numeroAleatorio = getNumeroAleatorio();
        String imageUrl = "https://randomuser.me/api/portraits/men/" + numeroAleatorio +".jpg";
        Picasso.get().load(imageUrl).into(imageView);

        // configuramos evento sobre imageView
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                numeroAleatorio = getNumeroAleatorio();
                String imageUrl = "https://randomuser.me/api/portraits/men/" + numeroAleatorio +".jpg";

                Picasso.get().load(imageUrl).into(imageView);

                //imageView.setImageResource(imagenes.get(contador));

            }
        });

        botnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // configuramos boton con evento de agregar datos a la lista
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // creamos el objeto ModeloPersona, para añadirlo al dao mediante el método setPersona()
                ModeloPersona persona = new ModeloPersona();
                persona.setNombre(String.valueOf(edt1.getText()));
                persona.setDescripcion(String.valueOf(edt2.getText()));
                persona.setFoto(numeroAleatorio);
/**
 * Cuando usas startActivityForResult, Android crea automáticamente un Intent para el Activity al que se va a llamar,
 * pero no reutiliza ese Intent cuando devuelves los resultados.
 * En cambio, debes crear un nuevo Intent para pasar los resultados de vuelta.
 */

                Intent resultadoIntent = new Intent();
                resultadoIntent.putExtra("persona", persona);  // Pasamos el objeto persona
                // Agregar datos adicionales al resultado si es necesario
                setResult(RESULT_OK, resultadoIntent);
                finish(); // Cierra la actividad de agregar
            }
        });
    }
    private int getNumeroAleatorio(){
        Random random = new Random();
        int numeroAleatorio = random.nextInt(51);
        return numeroAleatorio;
    }
}