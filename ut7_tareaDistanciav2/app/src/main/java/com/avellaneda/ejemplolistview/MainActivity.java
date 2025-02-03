package com.avellaneda.ejemplolistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.avellaneda.ejemplolistview.model.DAObbdd;
import com.avellaneda.ejemplolistview.model.MiBDHelper;
import com.avellaneda.ejemplolistview.model.ModeloPersona;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /**
     * Declaración de la constante para el código de resultado
     * El CODIGO_PARA_RESULTADO es un valor que tú mismo defines para identificar la solicitud de resultado.
     * Puede ser cualquier número entero único que elijas. A menudo se declara como una constante en tu clase de actividad
     * para que puedas usarlo en varios lugares de tu código.
     */
// update
    private static final int CODIGO_ENTRADA = 1;
    private static final int CODIGO_UPDATE = 2;

    private ListView lv;

    private Adaptador adaptador;
    private List<ModeloPersona> personas;

    private Context contex;

    private DAObbdd dao;
    private SQLiteDatabase instancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contex = this;
        instancia = MiBDHelper.obtenerInstancia(this);
        dao = new DAObbdd( instancia);
        personas =dao.obtenerTodasLasPersonas();

        adaptador = new Adaptador(this, personas);
        lv = (ListView) findViewById(R.id.lv);
        registerForContextMenu(lv);


        lv.setAdapter(adaptador);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    ModeloPersona persona = (ModeloPersona) adaptador.getItem(i);
                    Log.e("PERSONA", persona.getCodigo() + "-" + persona.getNombre());
                    Toast.makeText(getBaseContext(), "El código es: " + persona.getCodigo(), Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    e.printStackTrace();

                }

            }

        });


    }


    /*MENU BARRA*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_barra, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.salir) {
            // accion 1
            finish();
            return true;
        } else if (item.getItemId() == R.id.agregar) {
            // abrir EntradaDatos
            Intent intent = new Intent(this, EntradaDatos.class);
            //startActivity(intent);
            startActivityForResult(intent, CODIGO_ENTRADA);
            personas =dao.obtenerTodasLasPersonas();
            adaptador.notifyDataSetChanged();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    @Override

    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int i = info.position;
        if (item.getItemId() == R.id.item1) {
            ModeloPersona persona = (ModeloPersona) adaptador.getItem(i);
            Log.e("PERSONA", persona.getCodigo() + "-" + persona.getNombre());
            Toast.makeText(this, "BORRAMOS ESTA PERSONA DE LA LISTA : " + persona.getNombre(), Toast.LENGTH_SHORT).show();
            personas.remove(persona);
            dao.eliminarPersona(persona.getCodigo());

            adaptador.notifyDataSetChanged();

//
        } else if (item.getItemId() == R.id.item3) {
            Intent intent = new Intent(this, EdicionDatos.class);
            intent.putExtra("index", i);
            intent.putExtra("titulo", "EDITAR DATOS");
            intent.putExtra("persona", personas.get(i));
            startActivityForResult(intent, CODIGO_UPDATE);
            // si lo hacemos con startActivity , no sabemos en el punto en el que regresa a la app
            //startActivity(intent);



        } else {
            return super.onContextItemSelected(item);
        }
        return true;

    }

    @Override

    public void onCreateContextMenu(ContextMenu menu, View v,

                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_UPDATE && resultCode == RESULT_OK) {
            // Actualiza el adaptador y notifica el cambio
            ModeloPersona personaActualizada = (ModeloPersona) data.getSerializableExtra("persona");

            // Actualizar la base de datos con la persona modificada
            if (personaActualizada != null) {
                dao.actualizarPersona(personaActualizada);
            }
            personas =dao.obtenerTodasLasPersonas();
            adaptador.setMiArrayList(personas);
            adaptador.notifyDataSetChanged();



        } else if (requestCode == CODIGO_ENTRADA&& resultCode == RESULT_OK) {
            // Actualiza el adaptador y notifica el cambio
            ModeloPersona personaActualizada = (ModeloPersona) data.getSerializableExtra("persona");

            // Actualizar la base de datos con la persona modificada
            if (personaActualizada != null) {
                dao.insertarPersona(personaActualizada);
            }
            personas =dao.obtenerTodasLasPersonas();

            adaptador.setMiArrayList(personas);
            adaptador.notifyDataSetChanged();


        }
    }
    @Override
    protected void onDestroy() {
        // Realiza tareas de limpieza y liberación de recursos aquí

        super.onDestroy();
        Toast.makeText(this, "SALE DE LA APLICACIÓN", Toast.LENGTH_SHORT).show();
        personas= null;
        dao.cerrarConexion();

    }

}
