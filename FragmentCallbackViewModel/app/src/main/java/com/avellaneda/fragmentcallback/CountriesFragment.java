package com.avellaneda.fragmentcallback;


import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.lang.ref.WeakReference;

public class CountriesFragment extends Fragment {

    private View rootView;

    private ListView listViewCountries;
    private ArrayAdapter<String> countryNamesAdapter;
    private Context context;
    private String [] countries;

    private SharedViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_countries,container,false);
        initUI();
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.app_name)+"->Select Country");
    }


    private void initUI(){
        context  = getContext();
        countries = getResources().getStringArray(R.array.countries);
        listViewCountries = (ListView)rootView.findViewById(R.id.listViewCountries);

        countryNamesAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,countries);
        listViewCountries.setAdapter(countryNamesAdapter);

        listViewCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                viewModel.selectItem(countries[i]);

            }
        });
    }
}
