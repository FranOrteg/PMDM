package com.avellaneda.ejemplopractica.fragmentos;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avellaneda.ejemplopractica.model.Cocktail;
import com.avellaneda.ejemplopractica.model.CocktailRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CocktailViewModel extends ViewModel {

    private MutableLiveData<List<Cocktail>> favoriteCocktails;
    private MutableLiveData<List<Cocktail>> cocktailsList;

    public CocktailViewModel() {
        favoriteCocktails = new MutableLiveData<>(new ArrayList<>());  // Inicializa la lista como vacía
        cocktailsList = new MutableLiveData<>(new ArrayList<>());
    }

    // Obtener la lista de favoritos
    public LiveData<List<Cocktail>> getFavoriteCocktails() {
        return favoriteCocktails;
    }

    public void setCocktailList(List<Cocktail> list) {
        favoriteCocktails.setValue(list);
    }

    public LiveData<List<Cocktail>> getCocktails() {
        return cocktailsList;
    }

    public void setCocktails(List<Cocktail> list) {
        cocktailsList.setValue(list);
    }

    // Método para actualizar un cóctel (añadir o eliminar de favoritos)
    public void updateCocktailInFavorites(Cocktail cocktail) {
        List<Cocktail> favorites = favoriteCocktails.getValue();
        List<Cocktail> lista = cocktailsList.getValue();
        if (favorites == null) {
            favorites = new ArrayList<>();
        }
        if (cocktail.isFavorite()) {
            // Si es favorito, añadirlo
            favorites.add(cocktail);


            for (Cocktail c1 : lista) {
                if (c1.getId().equalsIgnoreCase(cocktail.getId())) {
                    c1.setFavorite(true);
                }
            }


        }
        // Actualizar la lista en el ViewModel
        favoriteCocktails.setValue(favorites);
        cocktailsList.setValue(lista);
    }

    public void removeFavoriteById(String idDrink) {
        List<Cocktail> currentFavorites = favoriteCocktails.getValue();
        List<Cocktail> lista = cocktailsList.getValue();
        if (currentFavorites == null) return;

        List<Cocktail> updatedFavorites = new ArrayList<>(currentFavorites);
        Iterator<Cocktail> iterator = updatedFavorites.iterator();
        while (iterator.hasNext()) {
            Cocktail cocktail = iterator.next();
            if (cocktail.getId().equals(idDrink)) {
                iterator.remove();
                break;
            }
        }

        for (Cocktail c1 : lista) {
            if (c1.getId().equalsIgnoreCase(idDrink)) {
                c1.setFavorite(false);
            }
        }


        favoriteCocktails.setValue(updatedFavorites);
        cocktailsList.setValue(lista);
    }

}

