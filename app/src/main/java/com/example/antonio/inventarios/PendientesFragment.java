package com.example.antonio.inventarios;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.antonio.inventarios.models.Order;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


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

        final ArrayList<Order> ordersArray = new ArrayList<Order>();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final String TOKEN = preferences.getString("TOKEN","No Existe");
        String COMPANYID = preferences.getString("COMPANY_ID","No Existe");

        requestmethods.get("order", TOKEN, COMPANYID, "", new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result.toString());
                    if(obj.getBoolean("ok")) {
                        JSONArray orders = new JSONArray(obj.getString("orders"));
                        for (int i = 0; i < orders.length(); i++) {
                            JSONObject order =  orders.getJSONObject(i);
                            if (order.getString("employee_id").indexOf("No ha sido aceptado")>=0) {
                                Order tmpOrd = new Order(order.getInt("date"), "", order.getInt("date_delivery"), order.getString("company_id"), order.getInt("completed"));
                                ordersArray.add(tmpOrd);
                            }
                        }
                        adaptadorPendientes pendientes = new adaptadorPendientes(getContext(), ordersArray);
                        listView.setAdapter(pendientes);
                        //Toast.makeText(context, ordersArray.size()+"", Toast.LENGTH_SHORT).show();
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

            }
        });

    }


}