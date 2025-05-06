package com.avellaneda.ejemplopractica;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import com.avellaneda.ejemplopractica.fragmentos.CocktailListFragment;
import com.avellaneda.ejemplopractica.fragmentos.CocktailViewModel;
import com.avellaneda.ejemplopractica.fragmentos.FavoriteCocktailFragment;
import com.avellaneda.ejemplopractica.fragmentos.SettingsViewModel;
import com.avellaneda.ejemplopractica.model.Cocktail;
import com.avellaneda.ejemplopractica.model.CocktailRepository;


import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private CocktailViewModel cocktailViewModel;
    private CocktailRepository cocktailRepository;
    private List<Cocktail> cocktails;
    private List<Cocktail> favorites;
    private SettingsViewModel settingsViewModel;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        cocktailRepository = new CocktailRepository(this);
        cocktailViewModel = new ViewModelProvider(this).get(CocktailViewModel.class);
        settingsViewModel= new ViewModelProvider(this).get(SettingsViewModel.class);
        settingsViewModel.loadFromPreferences(this);
        // Obtener los datos serializados del SplashActivity
        if (getIntent() != null && getIntent().hasExtra("cocktail_list")) {
            // Aquí obtenemos la lista de cócteles pasada desde el SplashActivity
            cocktails = (List<Cocktail>) getIntent().getSerializableExtra("cocktail_list");
            cocktailViewModel.setCocktails(cocktails);
            loadFavoritesFromDatabase();
            actualizarLista();

            // Cargar el fragment de la lista de cócteles con los datos
            CocktailListFragment listFragment = CocktailListFragment.newInstance(cocktails);
            // Iniciar la transacción de fragmentos
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainerTop, listFragment);
          //  transaction.commit();

            // Cargar el fragment inferior (de favoritos)
            FavoriteCocktailFragment favoriteFragment = new FavoriteCocktailFragment();
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainerBottom, favoriteFragment);
            transaction.commit();
        }
    }
    // Actualizar la lista con los favoritos
    private void actualizarLista(){
        // recorrer la lista de favoritos
        // si es favorito actualizarlo en cocktails
        for(Cocktail c: favorites){
            if(c.isFavorite()){
               for(Cocktail c1: cocktails){
                   if(c1.getId().equalsIgnoreCase(c.getId()) ){
                       c1.setFavorite(true);
                   }
               }
            }
        }
        // actualizamos el viewModel
        cocktailViewModel.setCocktails(cocktails);
    }
    // Actualizar la lista de favoritos desde la base de datos
    private void loadFavoritesFromDatabase() {
        cocktailRepository.open();
        favorites = cocktailRepository.getAllFavorites();
        cocktailRepository.close();
        cocktailViewModel.setCocktailList(favorites);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId() == R.id.action_increase_font) {
            Float currentSize = settingsViewModel.getFontSize().getValue();
            if (currentSize != null) {
                settingsViewModel.setFontSize(currentSize + 2f,context); // Aumenta en 2 puntos

            }
            return true;
        }else if(item.getItemId() == R.id.action_decrease_font){
            Float currentSize = settingsViewModel.getFontSize().getValue();
            if (currentSize != null) {
                settingsViewModel.setFontSize(currentSize - 2f,context); // Aumenta en 2 puntos

            }
        }

        return super.onOptionsItemSelected(item);
    }

}