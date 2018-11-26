package com.example.antonio.inventarios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.antonio.inventarios.models.Inventarios;

import java.util.ArrayList;

public class adaptadorAlmacen extends BaseAdapter{
    Context context;
    ArrayList<Inventarios> list;

    public adaptadorAlmacen(Context context, ArrayList<Inventarios> list) {
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
        vista = inflater.inflate(R.layout.inventarios, null);

        TextView title = (TextView) vista.findViewById(R.id.textView);
        TextView desc = (TextView) vista.findViewById(R.id.textView2);

        title.setText(list.get(position).getDir());
        desc.setText(list.get(position).getCity());


        return vista;
    }

}