package com.avellaneda.ejemplopractica.fragmentos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.avellaneda.ejemplopractica.EliminarFavoritoActivity;
import com.avellaneda.ejemplopractica.R;
import java.util.List;
import com.avellaneda.ejemplopractica.model.*;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteCocktailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteCocktailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SettingsViewModel settingsViewModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CocktailViewModel cocktailViewModel;
    private ListView listView;
    private CocktailAdapter cocktailAdapter;
    private ActivityResultLauncher<Intent> deleteFavoriteLauncher;
    public FavoriteCocktailFragment() {
        // Required empty public constructor
    }

    public static FavoriteCocktailFragment newInstance(String param1, String param2) {
        FavoriteCocktailFragment fragment = new FavoriteCocktailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_favorite_cocktail, container, false);
        listView= rootView.findViewById(R.id.listViewCocktails);
        cocktailViewModel = new ViewModelProvider(getActivity()).get(CocktailViewModel.class);
        settingsViewModel = new ViewModelProvider(requireActivity()).get(SettingsViewModel.class);

        // Observar los cambios en la lista de favoritos
        cocktailViewModel.getFavoriteCocktails().observe(getViewLifecycleOwner(), new Observer<List<Cocktail>>() {
            @Override
            public void onChanged(List<Cocktail> cocktails) {
                cocktailAdapter = new CocktailAdapter(getContext(), cocktails,cocktailViewModel);
                listView.setAdapter(cocktailAdapter);
            }
        });
        // Suscribirse a los cambios en fontSize
        settingsViewModel.getFontSize().observe(getViewLifecycleOwner(), new Observer<Float>() {
            @Override
            public void onChanged(Float fontSize) {
                // Actualizar el adaptador con el nuevo tamaño de fuente
                if (cocktailAdapter != null) {
                    cocktailAdapter.setFontSize(fontSize);
                }
            }
        });
        // Suscribirse a los cambios en fontType
        settingsViewModel.getFontType().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String fontType) {
                // Actualizar el adaptador con el nuevo tipo de fuente
                if (cocktailAdapter != null) {
                    cocktailAdapter.setFontType(fontType);
                }
            }
        });

        listView.setAdapter(cocktailAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Cocktail cocktailSeleccionado = (Cocktail) parent.getItemAtPosition(position);

            // Iniciar una actividad con intención de eliminar
            Intent intent = new Intent(requireContext(), EliminarFavoritoActivity.class);
            intent.putExtra("cocktail", cocktailSeleccionado);
            deleteFavoriteLauncher.launch(intent);
        });
        deleteFavoriteLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        String idDrinkDeleted = result.getData().getStringExtra("idDrink");
                        // Aquí puedes eliminarlo del ViewModel o actualizar la lista en el fragmento
                        cocktailViewModel.removeFavoriteById(idDrinkDeleted);  // Por ejemplo
                    }
                }
        );
        return rootView;
        // Inflate the layout for this fragment

    }

}