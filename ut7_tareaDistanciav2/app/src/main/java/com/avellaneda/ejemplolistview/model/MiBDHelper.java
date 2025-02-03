package com.avellaneda.ejemplolistview.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * SQLiteOpenHelper proporciona métodos para obtener instancias de SQLiteDatabase
 * La recomendación común es tener una única instancia de  MiBDHelper que se comparta
 * en toda la aplicación para garantizar una gestión coherente de la bbdd.
 * Ésto puede ser más eficiente que crear una nueva instancia de SQLiteOpenHelper cada vez que se requiera y puede lograrse
 * mediante un diseño Singleton.
 */
public class MiBDHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "miBaseDeDatos.db";
    private static final int VERSION_BD = 1;


    private static MiBDHelper instancia;
    private SQLiteDatabase baseDeDatos;
    public static final String TABLE_PERSONA = "ModeloPersona";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String COLUMN_CODIGO = "codigo";
    public static final String COLUMN_FOTO = "foto";


    // constructor privado
    private MiBDHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    /**
     * Usa getApplicationContext() cuando:
     *
     * Estás creando instancias que vivirán más tiempo que una Activity (Singletons, bases de datos, servicios globales).
     * No necesitas acceso a recursos específicos de la Activity (como Theme, UI, etc.).

     */
    public static synchronized SQLiteDatabase obtenerInstancia(Context context) {
        if (instancia == null) {
            instancia = new MiBDHelper(context.getApplicationContext());
        }
        return instancia.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_PERSONA + "("
                + COLUMN_CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOMBRE + " TEXT, "
                + COLUMN_DESCRIPCION + " TEXT, "
                + COLUMN_FOTO + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Código para actualizar la base de datos cuando el número de versión cambia
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONA);

        onCreate(db);
    }

}