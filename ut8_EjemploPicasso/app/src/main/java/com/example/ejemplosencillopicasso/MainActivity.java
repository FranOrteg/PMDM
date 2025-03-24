package com.example.ejemplosencillopicasso;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView imageViewNormal = findViewById(R.id.imageViewNormal);
        ImageView imageViewPlaceholder = findViewById(R.id.imageViewPlaceholder);
        ImageView imageViewResize = findViewById(R.id.imageViewResize);
        ImageView imageViewError = findViewById(R.id.imageViewError);


        String imageUrl = "https://fastly.picsum.photos/id/1005/200/300.jpg?hmac=ZygrmRTuNYz9HivXcWqFGXDRVJxIHzaS-8MA0I3NKBw";

        // 1. Cargar una imagen normalmente
        Picasso.get()
                .load(imageUrl)
                .into(imageViewNormal);

        // 2. Cargar una imagen con un placeholder mientras se descarga
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.placeholder) // Imagen de recurso local
                .into(imageViewPlaceholder);

        // 3. Redimensionar la imagen
        Picasso.get()
                .load(imageUrl)
                .resize(40, 40)
                .centerCrop() // Recortar para llenar el espacio
                .into(imageViewResize);

        // 4. Cargar una imagen con manejo de error
        Picasso.get()
                .load("https://invalid-url.com/image.png") // URL inválida para simular error
                .error(R.drawable.error_image) // Imagen en caso de error
                .into(imageViewError);
    }
}
