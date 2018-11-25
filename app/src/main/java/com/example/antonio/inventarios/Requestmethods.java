package com.example.antonio.inventarios;

import android.content.Context;
import android.telecom.Call;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Requestmethods {
    private String PATH = "https://rest-inventario.herokuapp.com/";
    RequestQueue requestQueue;

    public Requestmethods(Context context){

        requestQueue = Volley.newRequestQueue(context);
    }
    //GET
    public String get(String urlApi, final String token, String id, final VolleyCallback Callback){
        final String[] res = {""};
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                PATH+urlApi+id,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Callback.onSuccess(response.toString());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }){
            public Map<String, String> getHeaders(){
                HashMap<String, String> headers = new HashMap<String, String>();

                headers.put("token", token);
                return headers;
            }
        };
        requestQueue.add(jsonObjReq);
        return res[0];
    }
    //DELETE
    public String delete(String urlApi, final String token, String id, final VolleyCallback Callback){
        final String[] res = {""};
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.DELETE,
                PATH+urlApi+id,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Callback.onSuccess(response.toString());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }){
            public Map<String, String> getHeaders(){
                HashMap<String, String> headers = new HashMap<String, String>();

                headers.put("token", token);
                return headers;
            }
        };
        requestQueue.add(jsonObjReq);
        return res[0];
    }
    //POST
    public void post(String urlApi, final String token, String id, String content, final VolleyCallback Callback) throws JSONException {
        final String[] res = {""};
        String contentArray[] = content.split(",");
        JSONObject objs = new JSONObject();
        for(int i = 0; i < contentArray.length; i++){
            String tmpValues[] = contentArray[i].split("=");
            objs.put(tmpValues[0], tmpValues[1]);
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                PATH+urlApi+id,
                objs,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Callback.onSuccess(response.toString());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }){
            public Map<String, String> getHeaders(){
                HashMap<String, String> headers = new HashMap<String, String>();

                headers.put("token", token);
                return headers;
            }
        };
        requestQueue.add(jsonObjReq);
    }
    //PUT
    public String put(String urlApi, final String token, String id, String content, final VolleyCallback Callback) throws JSONException {
        final String[] res = {""};
        String contentArray[] = content.split(",");
        JSONObject objs = new JSONObject();
        for(int i = 0; i < contentArray.length; i++){
            String tmpValues[] = contentArray[i].split("=");
            objs.put(tmpValues[0], tmpValues[1]);
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.PUT,
                PATH+urlApi+id,
                objs,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Callback.onSuccess(response.toString());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }){
            public Map<String, String> getHeaders(){
                HashMap<String, String> headers = new HashMap<String, String>();

                headers.put("token", token);
                return headers;
            }
        };
        requestQueue.add(jsonObjReq);
        return res[0];
    }
}
