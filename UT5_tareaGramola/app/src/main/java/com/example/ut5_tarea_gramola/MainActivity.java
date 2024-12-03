package com.example.ut5_tarea_gramola;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ut5_tarea_gramola.databinding.ActivityMainBinding; // Clase generada automáticamente

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private View contextMenuElement;
    private static final int itemInfo = R.id.itemInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        // Inicializar el binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setBinding();

    }

    private void setBinding() {
        // Asignar el menu contextual a los 4 elementos
        registerForContextMenu(binding.ivcandy);
        registerForContextMenu(binding.ivcecilia);
        registerForContextMenu(binding.ivsatellite);
        registerForContextMenu(binding.ivziggy);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
        contextMenuElement = v; // Guardamos la vista que lanzó el menú
    }

    //@Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent i;
        // Para poder conocer qué elemento ha sido pulsado, obtenemos su id
        // es necesario "capturarlo" en el onCreateContextMenu
        int id = contextMenuElement.getId();
        //inicialización a un tipo página vacío no nula
        String url = "http:about:blank";

        if (item.getItemId() == R.id.itemInfo) {
            String cadena = "";
            if (id == binding.ivsatellite.getId())
                cadena = "Titulo: Satellite Of Love.\nAutor: Lou Reed.\nDisco: Trasnformer.\nAño: 1972";
            if (id == binding.ivziggy.getId())
                cadena = "Titulo: Ziggy Stardust.\nAutor: David Bowie.\nDisco: The Rise And Fall Of Ziggy Stardust And The Spiders From Mars.\nAño: 1972";
            if (id == binding.ivcecilia.getId())
                cadena = "Titulo: Cecilia.\nAutor: Simon & Garfunkel.\nDisco: Bridge Over Troubled Water.\nAño: 1970";
            if (id == binding.ivcandy.getId())
                cadena = "Titulo: Candy.\nAutor: Iggy Pop.\nDisco: Brick By Brick.\nAño: 1990";

            Toast t;
            t = Toast.makeText(this, cadena, Toast.LENGTH_LONG);
            t.show();
        } else if (item.getItemId() == R.id.itemIrWeb) {

            if (id == binding.ivsatellite.getId()) {
                url = "https://en.wikipedia.org/wiki/Satellite_of_Love";
            } else if (id == binding.ivcecilia.getId()) {
                url = "https://en.wikipedia.org/wiki/Cecilia_(Simon_%26_Garfunkel_song)";
            } else if (id == binding.ivziggy.getId()) {
                url = "https://en.wikipedia.org/wiki/Ziggy_Stardust_(song)";
            } else if (id == binding.ivcandy.getId()) {
                url = "https://en.wikipedia.org/wiki/Candy_(Iggy_Pop_song)";
            }
            i = new Intent("android.intent.action.VIEW", Uri.parse(url));
            startActivity(i);

        }
        return true;
    }
}