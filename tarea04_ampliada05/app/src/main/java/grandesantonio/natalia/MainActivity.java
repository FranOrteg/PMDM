package grandesantonio.natalia;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.ImageView;



import android.content.Context;

import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button boton;
    private EditText etnombre, etapellido, etedad, ettelefono, etpassword;
    private ArrayList<Contacto> contactos;
    private Context context;
    private ImageView imageView;
    private int[] imageArray = {
            R.drawable.foto1,
            R.drawable.foto2,
            R.drawable.foto3,
            R.drawable.foto4
            // Agrega aquí más imágenes
    };
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        contactos = DAO_contactos.getContactos();
        boton = findViewById(R.id.registro);
        etnombre = findViewById(R.id.etnombre);
        etapellido = findViewById(R.id.etapellido);
        etedad = findViewById(R.id.etedad);
        ettelefono = findViewById(R.id.ettelefono);
        etpassword = findViewById(R.id.etpassword);
        imageView=findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % imageArray.length;
                imageView.setImageResource(imageArray[currentIndex]);

            }
        });
        boton.setOnClickListener(v -> {

//
// crea Contacto con todos los campos de la pantalla
            Contacto miContacto = new Contacto();
            try{
                String nombre = etnombre.getText().toString();
                miContacto.setNombre(nombre);
                miContacto.setEdad(Integer.parseInt(etedad.getText().toString()));
                miContacto.setApellido(etapellido.getText().toString());
                miContacto.setPassword(etpassword.getText().toString());
                miContacto.setTelefono(Integer.parseInt(ettelefono.getText().toString()));
                miContacto.setAvatar(imageArray[currentIndex]);
                contactos.add(miContacto);

                Intent intent = new Intent(context, SegundaActivity.class);

                intent.putExtra("contactos", contactos);
                /*
                intent.putExtra("variable2", etapellido.getText().toString());
                intent.putExtra("numero", Integer.parseInt(etedad.getText().toString()));
                intent.putExtra("objeto", miContacto);*/


                startActivity(intent);
                Toast.makeText(context,  miContacto.toString(), Toast.LENGTH_SHORT).show();}
            catch (Exception e){
                Toast.makeText(context, "ERROR EN  ALGÚN DATO", Toast.LENGTH_SHORT).show();
            }
        });
    }
}