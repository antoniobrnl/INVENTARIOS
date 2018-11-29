package com.example.antonio.inventarios;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
public class RealizadosFragment extends Fragment {
    private Context context;
    Requestmethods requestmethods;

    @SuppressLint("ValidFragment")
    public RealizadosFragment(Context ctx) {
        context = ctx;
        requestmethods = new Requestmethods(ctx);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_realizados, container, false);

    }


    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        final ListView listView = (ListView) getView().findViewById(R.id.list_realizados);

        final ArrayList<Order> ordersArray = new ArrayList<Order>();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final String TOKEN = preferences.getString("TOKEN", "No Existe");
        final String COMPANYID = preferences.getString("COMPANY_ID", "No Existe");
        final String ID_EM = preferences.getString("ID_EM", "No Existe");

        requestmethods.get("order", TOKEN, COMPANYID, "", new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result.toString());
                    if (obj.getBoolean("ok")) {
                        JSONArray orders = new JSONArray(obj.getString("orders"));
                        for (int i = 0; i < orders.length(); i++) {
                            JSONObject order = orders.getJSONObject(i);
                            if (order.getString("employee_id").indexOf(ID_EM) >= 0) {
                                Order tmpOrd = new Order(order.getString("_id"), order.getInt("date"), "ID_EM", order.getInt("date_delivery"), order.getString("company_id"), order.getInt("completed"));
                                ordersArray.add(tmpOrd);
                            }
                        }
                        adaptadorRealizados realizados = new adaptadorRealizados(getContext(), ordersArray);
                        listView.setAdapter(realizados);
                        //Toast.makeText(context, ordersArray.size()+"", Toast.LENGTH_SHORT).show();
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Order select = new Order(ordersArray.get(i).getId(), ordersArray.get(i).getDate(), ordersArray.get(i).getEmployee_id(), ordersArray.get(i).getDate_delivery(), ordersArray.get(i).getCompany_id(), ordersArray.get(i).getCompleted());
                Intent intent = new Intent(context, product.class);
                intent.putExtra("orderID", select.getId());
                startActivity(intent);

            }

        });


    }
}