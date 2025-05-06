package com.avellaneda.ejemplopractica.model;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import  com.avellaneda.ejemplopractica.R;


import com.avellaneda.ejemplopractica.fragmentos.CocktailViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CocktailAdapter extends BaseAdapter {
    private CocktailRepository cocktailRepository;
    private Context context;
    private List<Cocktail> cocktails;
    private float fontSize;
    private String fontType;

    private CocktailViewModel cocktailViewModel;


    public CocktailAdapter(Context context, List<Cocktail> cocktails,CocktailViewModel cocktailViewModel) {
        this.context = context;
        this.cocktails = (cocktails != null) ? cocktails : new ArrayList<>();
        //this.cocktails = cocktails;
        cocktailRepository = new CocktailRepository(context);
        this.cocktailViewModel = cocktailViewModel;

        this.fontSize = 14f; // Tamaño predeterminado
        this.fontType = "sans-serif"; // Tipo de letra predeterminado

    }
    // Método para actualizar el tamaño de la fuente
    public void setFontSize(float size) {
        this.fontSize = size;
        notifyDataSetChanged(); // Notificar cambios en la lista para redibujar
    }

    // Método para actualizar el tipo de letra
    public void setFontType(String type) {
        this.fontType = type;
        notifyDataSetChanged(); // Notificar cambios en la lista para redibujar
    }
    @Override
    public int getCount() {
        return cocktails != null ? cocktails.size() : 0;

    }

    @Override
    public Object getItem(int position) {
        return cocktails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageView imageCocktail;
        TextView textName;
        ImageView iconFavorite;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            holder = new ViewHolder();
            holder.imageCocktail = convertView.findViewById(R.id.imageCocktail);
            holder.textName = convertView.findViewById(R.id.textName);
            holder.iconFavorite = convertView.findViewById(R.id.iconFavorite);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Cocktail cocktail = cocktails.get(position);

        holder.textName.setText(cocktail.getName());
        holder.textName.setTextSize(fontSize);
        holder.textName.setTypeface(Typeface.create(fontType, Typeface.NORMAL));

        //  Aquí usamos Picasso para cargar la imagen desde la URL
        Picasso.get()
                .load(cocktail.getImageUrl())
                .placeholder(R.drawable.logo_cocktail) // opcional: mientras carga
                .error(R.drawable.error)             // opcional: si falla
                .into(holder.imageCocktail);

        holder.iconFavorite.setImageResource(
                cocktail.isFavorite() ? R.drawable.favorito : R.drawable.no_favorito
        );
        /**
         * Adaptador: El adaptador será responsable de manejar el evento de favorito,
         * lo que implica guardarlo en la base de datos.
         * Luego, actualizará el ViewModel con la nueva lista de favoritos (si es necesario).
         */
        holder.iconFavorite.setOnClickListener(v -> {
            if(!cocktail.isFavorite()){
                cocktail.setFavorite(true);
                cocktailRepository.open();
                cocktailRepository.addFavorite(cocktail);
                cocktailRepository.close();
                Toast.makeText(context, "Favorito guardado", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                // Actualizar la lista de favoritos en el ViewModel
                cocktailViewModel.updateCocktailInFavorites(cocktail);  // Esto notifica al ViewModel que se actualizó la lista
            }
        });

        return convertView;
    }

}
