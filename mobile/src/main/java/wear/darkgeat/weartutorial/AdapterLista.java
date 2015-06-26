package wear.darkgeat.weartutorial;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import wear.darkgeat.utilities.Item;

/**
 * Created by darkgeat on 20/03/15.
 */
public class AdapterLista extends ArrayAdapter<Item> {

    private Activity context;
    private Item[] datos;

    public AdapterLista(Activity context,Item[] objects) {
        super(context, R.layout.item_lista, objects);
        this.context = context;
        datos = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.item_lista,null);

        ImageView imagen = (ImageView)v.findViewById(R.id.imageItemLista);
        imagen.setImageResource(datos[position].getImagen());

        TextView texto = (TextView)v.findViewById(R.id.textItemLista);
        texto.setText(datos[position].getMensaje());

        return v;
    }
}
