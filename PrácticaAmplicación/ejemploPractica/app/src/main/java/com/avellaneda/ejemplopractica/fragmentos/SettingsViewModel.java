package com.avellaneda.ejemplopractica.fragmentos;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    private MutableLiveData<Float> fontSize = new MutableLiveData<>(14f); // Tamaño por defecto
    private MutableLiveData<String> fontType = new MutableLiveData<>("sans-serif"); // Tipo de fuente por defecto
    private static final String PREF_NAME = "settings_prefs";
    private static final String KEY_FONT_SIZE = "font_size";
    private static final String KEY_FONT_TYPE = "font_type";

    // Getter y Setter para el tamaño de fuente
    public LiveData<Float> getFontSize() {
        return fontSize;
    }

    public void setFontSize(float size,Context context) {
        fontSize.setValue(size);
        saveToPreferences(context);
    }

    // Getter y Setter para el tipo de fuente
    public LiveData<String> getFontType() {
        return fontType;
    }

    public void setFontType(String type,Context context) {
        fontType.setValue(type);
        saveToPreferences(context);
    }

    public void loadFromPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        fontSize.setValue(prefs.getFloat(KEY_FONT_SIZE, 16f));
        fontType.setValue(prefs.getString(KEY_FONT_TYPE, "sans-serif"));
    }

    private void saveToPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(KEY_FONT_SIZE, fontSize.getValue());
        editor.putString(KEY_FONT_TYPE, fontType.getValue());
        editor.apply();
    }
}
