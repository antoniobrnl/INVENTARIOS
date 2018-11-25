package com.example.antonio.inventarios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class adaptadorAlmacen extends BaseAdapter{
    Context context;
    List<Inventarios> list;

    public adaptadorAlmacen(Context context, List<Inventarios> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        vista = inflater.inflate(R.layout.fragment_inventarios, null);

        TextView title = (TextView) vista.findViewById(R.id.titulo);
        TextView desc = (TextView) vista.findViewById(R.id.contenido);

        title.setText(list.get(position).getTitulo());
        desc.setText(list.get(position).getContenido());


        return vista;
    }

}