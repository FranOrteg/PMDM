package com.avellaneda.fragmentcallback;


import android.content.Context;
import android.os.Bundle;

import android.util.Log;
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


import java.lang.ref.WeakReference;

public class CountriesFragment extends Fragment {

    View rootView;

    ListView listViewCountries;
    ArrayAdapter<String> countryNamesAdapter;
    Context context;
    String [] countries;

    CallBackInterface callBackInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_countries,container,false);
        initUI();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.app_name)+"->Select Country");
    }

      @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CallBackInterface) {
            callBackInterface = (CallBackInterface) context;
        } else {
            throw new RuntimeException(context.toString() + " debe implementar OnDatoEnviadoListener");
        }
    }

    private void initUI(){
        context  = getContext();
        countries = getResources().getStringArray(R.array.countries);
        listViewCountries = (ListView)rootView.findViewById(R.id.listViewCountries);

        countryNamesAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,countries);
        listViewCountries.setAdapter(countryNamesAdapter);

        listViewCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if(callBackInterface!=null){
                callBackInterface.callBackMethod(countries[i]);

                Log.d("TAG - countries", countries[i]);
            }
            }
        });
    }
}
