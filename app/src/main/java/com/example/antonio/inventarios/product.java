package com.example.antonio.inventarios;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.example.antonio.inventarios.adapters.adaptadorProduct;
import com.example.antonio.inventarios.models.OrderItem;
import com.example.antonio.inventarios.models.Product;
import com.example.antonio.inventarios.models.produtList;
import com.pedro.library.AutoPermissions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kotlin.text.Regex;

public class product extends AppCompatActivity {
    private Toolbar toolbar;
    private static final int REQUEST_CODE_QR_SCAN = 101;



    Requestmethods requestmethods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ORDEN");

        //BOTON QR
        Button btn_leer = (Button) findViewById(R.id.leer);
        btn_leer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AutoPermissions.Companion.loadAllPermissions(product.this, 1);
                Intent i = new Intent(product.this, QrCodeActivity.class);
                startActivityForResult(i, REQUEST_CODE_QR_SCAN);
            }
        });

        //BOTON CANCELAR
        final Button btn_can = (Button) findViewById(R.id.cancelar);
        btn_can.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                requestmethods = new Requestmethods(getApplicationContext());
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                final String ID_EM = preferences.getString("ID_EM","No Existe");
                final String TOKEN = preferences.getString("TOKEN","No Existe");
                final String COMPANYID = preferences.getString("COMPANY_ID","No Existe");
                Bundle Extras = getIntent().getExtras();
                final String orderID = Extras.getString("orderID");
                try {
                    requestmethods.put("order/",TOKEN,COMPANYID,orderID,"completed=0,employee_id='No ha sido aceptado'",new VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(getApplicationContext(), "Se añadió a Pendientes", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //BOTON TERMINAR
        final Button btn_ter = (Button) findViewById(R.id.terminar);
        btn_ter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                requestmethods = new Requestmethods(getApplicationContext());
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                final String ID_EM = preferences.getString("ID_EM","No Existe");
                final String TOKEN = preferences.getString("TOKEN","No Existe");
                final String COMPANYID = preferences.getString("COMPANY_ID","No Existe");
                Bundle Extras = getIntent().getExtras();
                final String orderID = Extras.getString("orderID");
                try {
                    requestmethods.put("order/",TOKEN,COMPANYID,orderID,"completed=2,employee_id='"+ID_EM+"'",new VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(getApplicationContext(), "Pedido Terminado", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        requestmethods = new Requestmethods(getApplicationContext());
        final ListView listView = findViewById(R.id.productos);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String TOKEN = preferences.getString("TOKEN","No Existe");
        final String COMPANYID = preferences.getString("COMPANY_ID","No Existe");
        Bundle Extras = getIntent().getExtras();
        final String orderID = Extras.getString("orderID");

        final ArrayList<OrderItem> ordersItemArray = new ArrayList<OrderItem>();
        final ArrayList<Product> productArray = new ArrayList<Product>();
        final produtList list = new produtList();
        final int[] ont = {0};
        requestmethods.get("orderItem/",TOKEN,COMPANYID,orderID, new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result.toString());
                    if(obj.getBoolean("ok")) {
                        final JSONArray orderItem = new JSONArray(obj.getString("ordersItems"));
                        for (int i = 0; i < orderItem.length(); i++) {
                            JSONObject order =  orderItem.getJSONObject(i);
                            OrderItem tmpOrd = new OrderItem(order.getString("OrderId"), order.getString("product_id"), order.getInt("qty"), order.getString("company_id"));
                            ordersItemArray.add(tmpOrd);
                            requestmethods.get("product/", TOKEN, COMPANYID, order.getString("product_id"), new VolleyCallback() {

                                @Override
                                public void onSuccess(String result) {
                                    try {
                                        JSONObject objProduct = new JSONObject(result.toString());
                                        JSONObject products = new JSONObject(objProduct.getString("product"));

                                        Product prd = new Product(products.getString("name"), products.getInt("qty"),
                                                products.getString("meta_info").replaceAll("[^\\dA-Za-z:, ]", "").replace(",",", "), products.getString("desc"),
                                                products.getString("img"), products.getInt("price"), products.getString("warehouse_id"),
                                                products.getString("company_id"));

                                        productArray.add(prd);

                                        if(productArray.size() == ordersItemArray.size()){

                                            list.setProductArray(productArray);
                                            list.setOrdersItemArray(ordersItemArray);
                                            adaptadorProduct realizados = new adaptadorProduct(getApplicationContext(), list);
                                            listView.setAdapter(realizados);

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            ont[0]++;
                        }



                    }else{

                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        requestmethods.get("order/", TOKEN, COMPANYID, orderID, new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject objOrder = new JSONObject(result.toString());

                    JSONObject order = new JSONObject(objOrder.getString("order"));
                    if(order.getInt("completed") == 2){
                        btn_can.setVisibility(View.INVISIBLE);
                        btn_ter.setVisibility(View.INVISIBLE);
                    }

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    //QR
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(getApplicationContext(), "No se pudo obtener una respuesta", Toast.LENGTH_SHORT).show();
            String resultado = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if (resultado != null) {
                Toast.makeText(getApplicationContext(), "No se pudo escanear el código QR", Toast.LENGTH_SHORT).show();
            }
            return ;
        }
        if (requestCode == REQUEST_CODE_QR_SCAN) {
            if (data != null) {
                String lectura = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
                Toast.makeText(getApplicationContext(), "Leído: " + lectura, Toast.LENGTH_SHORT).show();

            }
        }

    }

}


