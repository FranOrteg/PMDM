package com.avellaneda.fragmentcallback;



import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        addCountriesFragment();
        addCountryDescriptionFragment();
    }

    private void addCountriesFragment(){
        fragmentTransaction=fragmentManager.beginTransaction();

        CountriesFragment countryListFragment=new CountriesFragment();

        fragmentTransaction.add(R.id.fragmentContainer,countryListFragment);
        fragmentTransaction.commit();
    }

    private void addCountryDescriptionFragment(){
        fragmentTransaction=fragmentManager.beginTransaction();

        CountryDescriptionFragment countryDescriptionFragment=new CountryDescriptionFragment();

        fragmentTransaction.replace(R.id.fragmentContainer2,countryDescriptionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
