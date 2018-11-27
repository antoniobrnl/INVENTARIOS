package com.example.antonio.inventarios;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antonio.inventarios.models.OrderItem;

import java.util.ArrayList;

public class adaptadorPendientes extends RecyclerView.Adapter<adaptadorPendientes.ViewHolder> {

    private Context mContext;
    private ArrayList<OrderItem> mList ;

    adaptadorPendientes(Context context, ArrayList<OrderItem> list){
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.fragment_pendientes, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ImageView img = holder.img_ped;
        TextView num = holder.num_ped;
        TextView des = holder.des_ped;

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img_ped;
        TextView num_ped, des_ped;

        public ViewHolder(View itemView){
            super(itemView);

            img_ped = itemView.findViewById(R.id.item_image);
            num_ped = itemView.findViewById(R.id.num_ped);
            des_ped = itemView.findViewById(R.id.des_ped);
        }
    }

}
