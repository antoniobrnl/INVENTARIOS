package com.example.antonio.inventarios;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


@SuppressLint("ValidFragment")
public class PendientesFragment extends Fragment  {
    private Context context;
    Requestmethods requestmethods;

    @SuppressLint("ValidFragment")
    public PendientesFragment(Context ctx) {
        context = ctx;
        requestmethods = new Requestmethods(ctx);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pendientes, container, false);
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        final ListView listView = (ListView) getView().findViewById(R.id.list_pedido);

    }


}