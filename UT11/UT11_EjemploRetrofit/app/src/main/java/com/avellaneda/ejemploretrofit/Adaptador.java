package com.avellaneda.ejemploretrofit;



import android.content.Context;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.BaseAdapter;

import android.widget.TextView;





import java.util.ArrayList;

import java.util.List;



/**

 * Created by dell on 11/10/2017.

 */



public class Adaptador extends BaseAdapter {



    Context contexto; //contexto de la aplicacion

    List<Curso> cursos; //lista de datos a generar. Podemos usar tb un ArrayList



    public Adaptador(Context contexto, List<Curso> misCursos) {

        this.contexto = contexto;

        cursos = misCursos;

    }



    @Override

    public int getCount() {

        return cursos.size(); //Devuelve la cantidad de elementos de la lista

    }



    @Override

    public Object getItem(int i) { //Devuelve el objeto de la lista en la posición indicada como parametro

        return cursos.get(i);

    }



    @Override

    public long getItemId(int i) {

        return i;

    }



    @Override

    public View getView(int i, View view, ViewGroup viewGroup) { //Es el método que se ejecuta cuando se muestra en mi ListView cada item

        View vista=view;

        LayoutInflater inflate = LayoutInflater.from(contexto); //Obtenemos el contexto del item sobre el cual estamos trabajando del ListView

        vista=inflate.inflate(R.layout.itemlistview, null); //Consigo referenciar a la vista en sí



        TextView titulo=(TextView)vista.findViewById(R.id.tvTitulo);

        TextView subtitulo=(TextView)vista.findViewById(R.id.tvSubtitulo);

        TextView profesores=(TextView)vista.findViewById(R.id.tvProfesores);



        titulo.setText(cursos.get(i).getTitle().toString());

        subtitulo.setText(cursos.get(i).getSubtitle().toString());



        ArrayList<Instructor> instructores= (ArrayList<Instructor>) cursos.get(i).getInstructors();

        String cadenaInstructores="Profesores: \n";

        for(int j=0; j<instructores.size(); j++){

            cadenaInstructores+="\t\t"+instructores.get(j).getName()+"\n";

        }

        if(instructores.size()==0)

            cadenaInstructores+="\t\tNo asignado\n";

        profesores.setText(cadenaInstructores);





        return vista;



    }



}