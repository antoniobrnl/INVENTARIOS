package com.example.antonio.inventarios.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antonio.inventarios.R;
import com.example.antonio.inventarios.models.produtList;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class adaptadorProduct extends BaseAdapter {

    Context context;
    produtList list;

    public adaptadorProduct(Context context, produtList list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.getOrdersItemArray().size();
    }

    @Override
    public Object getItem(int position) {

        return list.getOrdersItemArray().get(position);
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
        vista = inflater.inflate(R.layout.products, null);

        TextView name = (TextView) vista.findViewById(R.id.name_producto);
        TextView disponibility = (TextView) vista.findViewById(R.id.dis_producto);
        TextView req_producto = (TextView) vista.findViewById(R.id.req_producto);
        TextView ubi_producto = (TextView) vista.findViewById(R.id.ubi_producto);
        ImageView img = (ImageView) vista.findViewById(R.id.item_image);

        name.setText(list.getProductArray().get(position).getName());
        disponibility.setText("Disponibilidad: "+Integer.toString(list.getProductArray().get(position).getQty()));
        req_producto.setText("Cantidad a salir: "+Integer.toString(list.getOrdersItemArray().get(position).getQty()));
        ubi_producto.setText(list.getProductArray().get(position).getMeta_info());
        if (list.getProductArray().get(position).getImg() != "")
            new DownloadImageTask(img).execute("https://rest-inventario.herokuapp.com/img/products/"+list.getOrdersItemArray().get(position).getCompany_id()+"/"+list.getProductArray().get(position).getImg());



        return vista;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
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