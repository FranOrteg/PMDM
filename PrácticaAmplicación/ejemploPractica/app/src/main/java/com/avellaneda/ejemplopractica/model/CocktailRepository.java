package com.avellaneda.ejemplopractica.model;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CocktailRepository {

    private SQLiteDatabase db;
    private CocktailDbHelper dbHelper;

    public CocktailRepository(Context context) {
        dbHelper = new CocktailDbHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Insertar un cóctel favorito
    public void addFavorite(Cocktail cocktail) {
        ContentValues values = new ContentValues();
        values.put(CocktailDbHelper.COLUMN_NAME, cocktail.getName());
        values.put(CocktailDbHelper.COLUMN_IMAGE_URL, cocktail.getImageUrl());
        values.put(CocktailDbHelper.COLUMN_ID_DRINK, cocktail.getId());
        values.put(CocktailDbHelper.COLUMN_FAV, cocktail.isFavorite()?1:0);
        values.put(CocktailDbHelper.COLUMN_FAV,cocktail.isFavorite());

        db.insertWithOnConflict(CocktailDbHelper.TABLE_FAVORITES, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }
    // Eliminar un cóctel favorito
    public void removeFavorite(Cocktail cocktail) {
        db.delete(
                CocktailDbHelper.TABLE_FAVORITES,
                CocktailDbHelper.COLUMN_ID_DRINK + " = ?",
                new String[]{cocktail.getId()}
        );
    }

    // Obtener todos los favoritos
    @SuppressLint("Range")
    public List<Cocktail> getAllFavorites() {
        List<Cocktail> favorites = new ArrayList<>();
        Cursor cursor = db.query(CocktailDbHelper.TABLE_FAVORITES,
                new String[]{CocktailDbHelper.COLUMN_NAME, CocktailDbHelper.
                        COLUMN_IMAGE_URL, CocktailDbHelper.COLUMN_ID_DRINK,CocktailDbHelper.COLUMN_FAV},
                null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Cocktail cocktail = new Cocktail();
                cocktail.setName(cursor.getString(cursor.getColumnIndex(CocktailDbHelper.COLUMN_NAME)));
                cocktail.setImageUrl(cursor.getString(cursor.getColumnIndex(CocktailDbHelper.COLUMN_IMAGE_URL)));
                cocktail.setId(cursor.getString(cursor.getColumnIndex(CocktailDbHelper.COLUMN_ID_DRINK)));
                cocktail.setFavorite(cursor.getInt(cursor.getColumnIndex(CocktailDbHelper.COLUMN_FAV)) == 1);
                favorites.add(cocktail);
            }
            cursor.close();
        }
        return favorites;
    }
}
