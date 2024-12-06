package grandesantonio.natalia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private Context context;
private ArrayList<Contacto> contactos;
public  Adaptador(Context context, ArrayList<Contacto> contactos){

    this.context = context;
    this.contactos = contactos;
}

    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int position) {
        return contactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contactos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item, null);

        TextView tx = convertView.findViewById(R.id.textView );
        ImageView im = convertView.findViewById(R.id.imageView3);

        tx.setText(contactos.get(position).getNombre());
        im.setImageResource(contactos.get(position).getAvatar());


        return convertView;

    }
}
