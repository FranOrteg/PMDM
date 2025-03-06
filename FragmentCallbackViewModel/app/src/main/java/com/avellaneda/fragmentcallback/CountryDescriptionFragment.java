package com.avellaneda.fragmentcallback;

/**
 * Created by anildeshpande on 12/26/17.
 */

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class CountryDescriptionFragment extends Fragment {
    private static final String COMMON_TAG = "CombinedLifeCycle";
    private static final String FRAGMENT_NAME = CountryDescriptionFragment.class.getSimpleName();

    private static final String TAG = COMMON_TAG;

    private View rootView;
    private TextView textViewCountryDescription;
    private SharedViewModel viewModel;

    String countryName;
    String countryDescription;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getSelectedItem().observe(this, item -> {
            // Si hay cambios en el dato
            // Usar el dato recibido
            Toast.makeText(getContext(), "Recibido: " + item, Toast.LENGTH_SHORT).show();
            actualizarTexto(item);
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_country_description,container,false);
        initUI();

        return rootView;
    }

    private void initUI(){
        textViewCountryDescription = (TextView)rootView.findViewById(R.id.textViewCountryDescription);
    }

    public void actualizarTexto(String dato) {
        countryDescription = getString(getStringId(dato));
        textViewCountryDescription.setText(countryDescription);

    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(countryName);
        textViewCountryDescription.setText(countryDescription);
    }

    private int getStringId(String countryName){
        if(countryName.equals("India")){
            return R.string.India;
        }else if(countryName.equals("USA")){
            return R.string.USA;
        }else if(countryName.equals("Pakistan")){
            return R.string.Pakistan;
        }else if(countryName.equals("Bangladesh")){
            return R.string.Bangladesh;
        }else if(countryName.equals("Egypt")){
            return R.string.Egypt;
        }else if(countryName.equals("Indonesia")){
            return R.string.Indonesia;
        }else if(countryName.equals("UK")){
            return R.string.UK;
        }else if(countryName.equals("Germany")){
            return R.string.Germany;
        }else {
            return R.string.India;
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
