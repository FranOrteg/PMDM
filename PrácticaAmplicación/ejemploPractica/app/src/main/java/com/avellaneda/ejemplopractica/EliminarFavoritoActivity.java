package com.avellaneda.ejemplopractica;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.avellaneda.ejemplopractica.model.Cocktail;
import com.avellaneda.ejemplopractica.model.CocktailRepository;

public class EliminarFavoritoActivity extends AppCompatActivity {

    private Cocktail cocktail;
    private CocktailRepository cocktailRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_favority);
        cocktailRepository = new CocktailRepository(this);

        cocktail = (Cocktail) getIntent().getSerializableExtra("cocktail");

        TextView nombre = findViewById(R.id.nombreCocktail);
        nombre.setText(cocktail.getName());

        Button btnEliminar = findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(v -> {
            eliminarDeFavoritos(cocktail.getId());
            Intent resultIntent = new Intent();
            resultIntent.putExtra("idDrink", cocktail.getId()); // Suponiendo que tienes el objeto Cocktail
            setResult(Activity.RESULT_OK, resultIntent);
            finish(); // Cierra el activity y devuelve el resultado

        });
    }

    private void eliminarDeFavoritos(String idDrink) {

        cocktailRepository.open();
        cocktailRepository.removeFavorite(cocktail);
        cocktailRepository.close();

    }
}
