package com.avellaneda.ejemploavmaps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;
import android.widget.Toast;

import com.avellaneda.ejemploavmaps.model.Localizacion;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Localizacion> localizacions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        try{
        localizacions = (ArrayList<Localizacion>) getIntent().getSerializableExtra("array");}
        catch (Exception ex){
            localizacions = new ArrayList<>();
        }
                Button buscar = findViewById(R.id.buscar);
        Button mapa = findViewById(R.id.mapa);
        EditText ciudad = findViewById(R.id.editTextText);
        TextView txLatitud=findViewById(R.id.numlat);
        TextView txLongitud=findViewById(R.id.numlong);


        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocultarTeclado();
                boolean success=false;
                String nombre = String.valueOf(ciudad.getText()).trim();
                for (Localizacion loc : localizacions) {

                    if (loc.getName().equalsIgnoreCase(nombre)) {
                        success= true;
                        txLatitud.setText(String.valueOf(loc.getCoord().getLat()));
                        txLongitud.setText(String.valueOf(loc.getCoord().getLon()));
                        Toast.makeText(getApplicationContext(), "Localidad: " + (loc.getName()) + " encontrada. ", Toast.LENGTH_SHORT).show();
                    }
                }
                if(!success){
                    Toast.makeText(getApplicationContext(), "Esta localidad no existe en nuestra base de datos", Toast.LENGTH_SHORT).show();
                    txLatitud.setText(getString(R.string.valorInicial));
                    txLongitud.setText(getString(R.string.valorInicial));
                }
            }
        });

        mapa.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                double latitud=Double.parseDouble(txLatitud.getText().toString());
                double longitud=Double.parseDouble(txLongitud.getText().toString());

                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                i.putExtra("latitud",latitud);
                i.putExtra("longitud",longitud);
                startActivity(i);
            }
        });

    }

    private void ocultarTeclado() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        }
    }

}
