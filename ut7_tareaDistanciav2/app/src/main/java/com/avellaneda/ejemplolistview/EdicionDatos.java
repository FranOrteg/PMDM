package com.avellaneda.ejemplolistview;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.avellaneda.ejemplolistview.model.ModeloPersona;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EdicionDatos extends AppCompatActivity {
    private static final int REQUEST_IMAGE_PICK = 1;

    private ImageView imageView;
    private Button btn,botnVolver;
    //private ArrayList<Integer> imagenes;
    private int contador;

    private EditText edt1, edt2;
    private TextView titulo;
    private ModeloPersona persona;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_datos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        String tituloExtra = intent.getStringExtra("titulo");

        persona = (ModeloPersona) intent.getSerializableExtra("persona" );
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        titulo = findViewById(R.id.titulo);
        titulo.setText(tituloExtra);
        imageView = findViewById(R.id.imageView);

        edt1.setText(persona.getNombre());
        edt2.setText(persona.getDescripcion());
        String imageUrl = "https://randomuser.me/api/portraits/men/" + persona.getFoto() +".jpg";

        Picasso.get().load(imageUrl).into(imageView);
        //imageView.setImageResource(persona.getFoto());
        btn = findViewById(R.id.btn);
        botnVolver = findViewById(R.id.btnVOLVER);
        //imagenes = DaoImagenes.devolverListaImagenes();
        // configuramos evento sobre imageView


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
                contador++;

                // creamos el objeto ModeloPersona, para añadirlo al dao mediante el método setPersona()

                persona.setNombre(String.valueOf(edt1.getText()));
                persona.setDescripcion(String.valueOf(edt2.getText()));

                Drawable drawable = imageView.getDrawable(); // Obtiene el Drawable asociado al ImageView
                Intent resultadoIntent = new Intent();
                resultadoIntent.putExtra("persona", persona);  // Pasamos el objeto persona
                // Agregar datos adicionales al resultado si es necesario
                setResult(RESULT_OK, resultadoIntent);
                finish(); // Cierra la actividad de agregar

            }

        });
    }
}