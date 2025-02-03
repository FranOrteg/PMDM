package com.avellaneda.ejemplolistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avellaneda.ejemplolistview.model.ModeloPersona;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class                             Adaptador extends BaseAdapter {

    private Context miContexto;

    private ArrayList<ModeloPersona> miArrayList;

    public Adaptador(Context miContexto, List<ModeloPersona> miArrayList) {

        this.miContexto = miContexto;
        this.miArrayList = (ArrayList<ModeloPersona>) miArrayList;
    }

    @Override
    public int getCount() {
        return miArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return miArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return miArrayList.get(i).getCodigo();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(miContexto);
        view = layoutInflater.inflate(R.layout.item, null);

        TextView nombre = (TextView) view.findViewById(R.id.tvNombre);
        TextView descripcion = (TextView) view.findViewById(R.id.tvDescripcion);
        ImageView foto = (ImageView) view.findViewById(R.id.imgFoto);
      /*
        // ejemplo de uso de popup menu
        foto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(miContexto, foto);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());


                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    /**
                     * @return
                     * true: Si has manejado el evento del clic y no quieres que el sistema haga nada más con él.
                     * false: Si no has manejado el evento y deseas que el sistema siga procesándolo (por ejemplo, si hay algún listener adicional configurado).
                     */
        /*
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(miContexto, "You Clicked " + menuItem.getItemId(), Toast.LENGTH_SHORT).show();
                        boolean success = false;
                        switch (menuItem.getItemId()) {
                            case R.id.item1:
                                Toast.makeText(miContexto, "You Clicked " + menuItem.getItemId(), Toast.LENGTH_SHORT).show();
                                // eliminar
                                miArrayList.remove(i);
                                notifyDataSetChanged();
                                success = true;
                                break;
                            case R.id.item2:
                                Toast.makeText(miContexto, "You Clicked " + menuItem.getItemId(), Toast.LENGTH_SHORT).show();
                                success = true;
                                // editar
                                // abre el activity con los datos para actualizar
                                break;
                            case R.id.item3:
                                Toast.makeText(miContexto, "You Clicked " + menuItem.getItemId(), Toast.LENGTH_SHORT).show();
                                success = true;
                                ;
                                // añadir
                                // abre el activity vacío
                                break;

                        }
                        return success;

                    }
                });
                popupMenu.show();
            }
        });
*/

        nombre.setText(miArrayList.get(i).getNombre());
        descripcion.setText((miArrayList.get(i).getDescripcion()));

        String imageUrl = "https://randomuser.me/api/portraits/men/" +  miArrayList.get(i).getFoto() +".jpg";
        //Glide.with(context).load(imageUrl).into(imageView);
        //// O con Picasso
        Picasso.get().load(imageUrl).into(foto);
       // foto.setImageResource(miArrayList.get(i).getFoto());
        return view;

    }
    public void setMiArrayList(List<ModeloPersona> miArrayList) {
        this.miArrayList = (ArrayList<ModeloPersona>) miArrayList;}

}