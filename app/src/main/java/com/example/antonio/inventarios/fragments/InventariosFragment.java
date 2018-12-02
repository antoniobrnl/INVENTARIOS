package com.example.antonio.inventarios.fragments;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.antonio.inventarios.R;
import com.example.antonio.inventarios.Requestmethods;
import com.example.antonio.inventarios.VolleyCallback;
import com.example.antonio.inventarios.adapters.adaptadorAlmacen;
import com.example.antonio.inventarios.models.Inventarios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class InventariosFragment extends Fragment {

    private Context context;
    Requestmethods requestmethods;

    @SuppressLint("ValidFragment")
    public InventariosFragment(Context ctx) {
        context = ctx;
        requestmethods = new Requestmethods(ctx);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventarios, container, false);
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        final ListView listView = (ListView) getView().findViewById(R.id.listWarehouse);

        final ArrayList<Inventarios> warehousesArray = new ArrayList<Inventarios>();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String TOKEN = preferences.getString("TOKEN","No Existe");
        String COMPANYID = preferences.getString("COMPANY_ID","No Existe");

        requestmethods.get("warehouse", TOKEN, COMPANYID, "", new VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject obj = new JSONObject(result.toString());
                    if(obj.getBoolean("ok")){
                        JSONArray warehouses = new JSONArray(obj.getString("warehouses"));
                        for (int i = 0; i < warehouses.length(); i++){
                            JSONObject warehouse =  warehouses.getJSONObject(i);


                            Inventarios tmpInv = new Inventarios(warehouse.getString("dir"), warehouse.getString("city"),
                                    warehouse.getInt("num"), Float.parseFloat(warehouse.getString("lat")),
                                    Float.parseFloat(warehouse.getString("lon")), warehouse.getString("company_id"));

                            warehousesArray.add(tmpInv);
                        }

                        adaptadorAlmacen almacen = new adaptadorAlmacen(getContext(), warehousesArray);
                        listView.setAdapter(almacen);
                    }else{

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<"+warehousesArray.get(i).getLat()+">," +
                        "<"+warehousesArray.get(i).getLon()+">?q=<"+warehousesArray.get(i).getLat()+">,<"+warehousesArray.get(i).getLon()+">(Almacen)"));
                startActivity(intent);

            }
        });

    }

}
