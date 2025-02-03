package com.avellaneda.ejemplolistview.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;


public class DAObbdd {
    private SQLiteDatabase db;



    public static final String TABLE_PERSONA = "ModeloPersona";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String COLUMN_CODIGO = "codigo";
    public static final String COLUMN_FOTO = "foto";

    public DAObbdd( SQLiteDatabase db) {

        this.db = db;
    }

    // Método para insertar una persona
    public long insertarPersona(ModeloPersona persona) {
        String nombre = persona.getNombre();
        String descripcion = persona.getDescripcion();
        int foto = persona.getFoto();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_DESCRIPCION, descripcion);
        values.put(COLUMN_FOTO, foto);

        long id = db.insert(TABLE_PERSONA, null, values);

        return id; // Devuelve el ID del nuevo registro
    }

    public List<ModeloPersona> obtenerTodasLasPersonas() {
        List<ModeloPersona> personas = new ArrayList<>();


        // Consultamos todos los registros de la tabla ModeloPersona
        Cursor cursor = db.query(TABLE_PERSONA,
                new String[]{COLUMN_CODIGO, COLUMN_NOMBRE, COLUMN_DESCRIPCION, COLUMN_FOTO},
                null, null, null, null, null);

        // Recorremos el cursor y agregamos los datos a la lista
        if (cursor != null) {
            int indexCodigo = cursor.getColumnIndex(COLUMN_CODIGO);
            int indexNombre = cursor.getColumnIndex(COLUMN_NOMBRE);
            int indexDescripcion = cursor.getColumnIndex(COLUMN_DESCRIPCION);
            int indexFoto = cursor.getColumnIndex(COLUMN_FOTO);

            // Verificamos si la columna existe (no devuelve -1)
            if (indexCodigo != -1 && indexNombre != -1 && indexDescripcion != -1 && indexFoto != -1) {
            while (cursor.moveToNext()) {
                int codigo = cursor.getInt(indexCodigo);
                String nombre = cursor.getString(indexNombre);
                String descripcion = cursor.getString(indexDescripcion);
                int foto = cursor.getInt(indexFoto);

                // Crear un objeto ModeloPersona y agregarlo a la lista
                ModeloPersona persona = new ModeloPersona(codigo, nombre, descripcion, foto);
                personas.add(persona);
            }
            cursor.close(); // Cerrar el cursor
        }
        } else {
            // Si alguna columna no existe, puedes manejarlo aquí, por ejemplo:
            Log.e("DBHelper", "Algunas columnas no se encuentran en la base de datos.");
        }


        return personas;
    }
    public boolean eliminarPersona(int codigo) {

        // Eliminar el registro donde el código coincide
        int filasEliminadas = db.delete(TABLE_PERSONA, COLUMN_CODIGO + " = ?", new String[]{String.valueOf(codigo)});

        // Si el número de filas eliminadas es mayor que 0, el registro fue eliminado con éxito
        return filasEliminadas > 0;
    }
    public boolean actualizarPersona(ModeloPersona persona) {

        // Crear un ContentValues para los nuevos valores
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, persona.getNombre());
        values.put(COLUMN_DESCRIPCION, persona.getDescripcion());
        values.put(COLUMN_FOTO, persona.getFoto());

        // Actualizar la persona donde el código coincide
        int filasActualizadas = db.update(TABLE_PERSONA, values, COLUMN_CODIGO + " = ?", new String[]{String.valueOf(persona.getCodigo())});


        // Si el número de filas actualizadas es mayor que 0, la actualización fue exitosa
        return filasActualizadas > 0;
    }
    public void cerrarConexion() {


        if(db != null) {
            db.close();
            db = null;
        }
    }
}
