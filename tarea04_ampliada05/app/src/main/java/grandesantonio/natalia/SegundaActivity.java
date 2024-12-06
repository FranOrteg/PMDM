package grandesantonio.natalia;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.view.View;

public class SegundaActivity extends AppCompatActivity {
    private ArrayList<Contacto> contactos;
    private ArrayList<String> nombres;
    private ListView lv1;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        context = this;
       contactos = (ArrayList<Contacto>)getIntent().getSerializableExtra("contactos");
       convertirArray();

        lv1 =(ListView)findViewById(R.id.lv1);

       /* ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nombres);*/

        Adaptador adaptador = new Adaptador(context, contactos);
        lv1.setAdapter(adaptador);



        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView adapterView, View view, int i, long l) {

             // TODO definir el evento que queremos hacer



            }

        });


    }


    private void convertirArray(){
     nombres = new ArrayList();
        for(Contacto c: contactos){
            nombres.add(c.getNombre() + ", "+ c.getApellido());
        }


    }
}