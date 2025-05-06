package com.avellaneda.ejemplopractica.model;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CocktailDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cocktail.db";
    private static final int DATABASE_VERSION = 1;

    // Definir la tabla y columnas
    public static final String TABLE_FAVORITES = "favorites";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMAGE_URL = "image_url";
    public static final String COLUMN_ID_DRINK = "id_drink";
    public static final String COLUMN_FAV = "favorite";

    public CocktailDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_FAVORITES + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_IMAGE_URL + " TEXT, "
                + COLUMN_ID_DRINK + " TEXT UNIQUE," +
                 COLUMN_FAV + " INTEGER )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        onCreate(db);
    }
}
