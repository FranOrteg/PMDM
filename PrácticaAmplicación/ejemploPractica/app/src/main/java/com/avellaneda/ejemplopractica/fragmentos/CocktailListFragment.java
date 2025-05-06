package com.avellaneda.ejemplopractica.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.avellaneda.ejemplopractica.R;
import com.avellaneda.ejemplopractica.model.Cocktail;
import com.avellaneda.ejemplopractica.model.CocktailAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CocktailListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CocktailListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Cocktail> cocktailList;
    private CocktailViewModel cocktailViewModel;
    private SettingsViewModel settingsViewModel;
    private  CocktailAdapter adapter;
    public CocktailListFragment() {
        // Required empty public constructor
    }

    public static CocktailListFragment newInstance(List<Cocktail> cocktails) {
        CocktailListFragment fragment = new CocktailListFragment();
        Bundle args = new Bundle();
        args.putSerializable("cocktail_list", (java.io.Serializable) cocktails);
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
        View view = inflater.inflate(R.layout.fragment_cocktail_list, container, false);
        ListView listView = view.findViewById(R.id.listViewCocktails);
        cocktailViewModel = new ViewModelProvider(requireActivity()).get(CocktailViewModel.class);
        settingsViewModel = new ViewModelProvider(requireActivity()).get(SettingsViewModel.class);
        // Suscribirse a los cambios en la lista de cocktails
        cocktailViewModel.getCocktails().observe(getViewLifecycleOwner(), cocktails -> {
            if (cocktails != null && !cocktails.isEmpty()) {
                cocktailList = cocktails;
                adapter = new CocktailAdapter(requireContext(), cocktailList, cocktailViewModel);
                listView.setAdapter(adapter);
            }
        });


        // Obtener la lista de cócteles desde el Bundle
        /*
        if (getArguments() != null) {
            cocktailList = (List<Cocktail>) getArguments().getSerializable("cocktail_list");
        }*/
        // Suscribirse a los cambios en fontSize
        settingsViewModel.getFontSize().observe(getViewLifecycleOwner(), new Observer<Float>() {
            @Override
            public void onChanged(Float fontSize) {
                // Actualizar el adaptador con el nuevo tamaño de fuente
                if (adapter != null) {
                    adapter.setFontSize(fontSize);
                }
            }
        });
        // Suscribirse a los cambios en fontType
        settingsViewModel.getFontType().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String fontType) {
                // Actualizar el adaptador con el nuevo tipo de fuente
                if (adapter != null) {
                    adapter.setFontType(fontType);
                }
            }
        });
        adapter = new CocktailAdapter(getActivity(), cocktailList,cocktailViewModel);
        // Configurar el ListView con un adaptador
        listView.setAdapter(adapter);

        return view;
    }
}