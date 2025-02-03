package com.avellaneda.ud08_ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detalle extends AppCompatActivity {

    private TextView tvMarca, tvDescripcion;

    private ImageView imgvLogo, imgvFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        tvMarca=(TextView)findViewById(R.id.tvMarca);
        tvDescripcion=(TextView)findViewById(R.id.tvDescripcion);
        imgvLogo=(ImageView)findViewById(R.id.imgvLogo);
        imgvFoto=(ImageView)findViewById(R.id.imgvFoto);



        Bundle bundle = getIntent().getExtras();
        int selector = bundle.getInt("selector");
        switch (selector) {

            case 0:
                // bmv
                imgvLogo.setImageResource(R.drawable.bultacologo);
/*
                Glide.with(this)
                        .load("https://arizonacustombike.com/arizonagarage/wp-content/uploads/2023/01/bultaco.jpg")
                        .into(imgvFoto);*/
                imgvFoto.setImageResource(R.drawable.bultaco);
                break;

            case 2:
                // harley
                imgvLogo.setImageResource(R.drawable.escudo_harley);
                imgvFoto.setImageResource(R.drawable.harley);
                break;

            case 1:
                // moto guzzi

                imgvLogo.setImageResource(R.drawable.escudo_motoguzzi);
                imgvFoto.setImageResource(R.drawable.motoguzzi);
                break;

        }



        tvMarca.setText(getResources().getStringArray(R.array.nombre)[selector]);

        tvDescripcion.setText(getResources().getStringArray(R.array.descripcion)[selector]);
    }
}