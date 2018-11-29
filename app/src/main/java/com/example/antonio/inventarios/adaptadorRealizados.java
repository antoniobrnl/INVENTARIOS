package com.example.antonio.inventarios;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.antonio.inventarios.models.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class adaptadorRealizados extends BaseAdapter {

    Context context;
    ArrayList<Order> list;


    public adaptadorRealizados(Context context, ArrayList<Order> list) {
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        vista = inflater.inflate(R.layout.realizados, null);

        TextView date = (TextView) vista.findViewById(R.id.date);
        TextView date_delivery = (TextView) vista.findViewById(R.id.date_delivery);
        TextView status = (TextView)vista.findViewById(R.id.status);

        date.setText("Fecha de Entrada: "+getDate(list.get(position).getDate()));
        date_delivery.setText("Fecha de Salida: "+getDate(list.get(position).getDate_delivery()));
        status.setText("Estado: "+estado(list.get(position).getCompleted()));


        return vista;
    }

    public String estado (int e){
        switch (e){
            case 1:
                return "En Proceso";
            case 2:
                return "Completado";
        }
        return "Sin Asignar";
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getDate(long fecha){
        Date date = new Date();
        date.setTime((long)fecha*1000);
        SimpleDateFormat mdyFormat = new SimpleDateFormat("dd/MM/yyyy");
        String mdy = mdyFormat.format(date);

        return mdy;
    }

}