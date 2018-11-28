package com.example.antonio.inventarios;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button btn;
    EditText user, pass;
    TextView token,companyid, id_em, nom_em, corr_em;
    private Requestmethods request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        request = new Requestmethods(getApplicationContext());
        btn = (Button)findViewById(R.id.loginBtn);
        user = (EditText)findViewById(R.id.txtuser);
        pass = (EditText)findViewById(R.id.txtpassword);
        token = (TextView)findViewById(R.id.token);
        companyid = (TextView)findViewById(R.id.companyid);

        id_em = (TextView)findViewById(R.id.id_empleado);
        nom_em = (TextView)findViewById(R.id.nombre_empleado);
        corr_em = (TextView)findViewById(R.id.correo_empleado);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuario = user.getText().toString();
                String contrasena = pass.getText().toString();
                String content = "email="+usuario+",password="+contrasena;
                try {
                    request.post("login","", "","",content,new VolleyCallback(){
                        @Override
                        public void onSuccess(String result){
                            try {
                                JSONObject obj = new JSONObject(result.toString());
                                if (obj.getBoolean("ok")) {
                                    token.setText(obj.getString("token"));
                                    JSONObject user = new JSONObject(obj.getString("user"));
                                    companyid.setText(user.getString("company_id"));
                                    id_em.setText(user.getString("_id"));
                                    nom_em.setText(user.getString("name")+" "+ user.getString("last_name"));
                                    corr_em.setText(user.getString("email"));
                                    guardarPreferencias();
                                    if (user.getBoolean("account_delete")==false && user.getInt("type")==1) {
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(),"Sesión: Iniciada", Toast.LENGTH_SHORT).show();
                                    } else{
                                        Toast.makeText(getApplicationContext(),"Este usuario ¡No puede acceder!", Toast.LENGTH_SHORT).show();
                                    }

                                }
                                if (obj.getBoolean("ok")!=true){
                                    Toast.makeText(getApplicationContext(), "Incorrecto usuario y/o contraseña", Toast.LENGTH_SHORT).show();
                                }

                                cargarPreferencias();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    //Boton retroceder deshabilitado
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //Aqui guardamos el token y el company_id gurdados en la memoria del celular
    private void guardarPreferencias(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String TOKEN = token.getText().toString();
        String COMPANY_ID = companyid.getText().toString();
        String ID_EM = id_em.getText().toString();
        String NOM_EM = nom_em.getText().toString();
        String CORR_EM = corr_em.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("TOKEN", TOKEN);
        editor.putString("COMPANY_ID", COMPANY_ID);
        editor.putString("ID_EM", ID_EM);
        editor.putString("NOM_EM", NOM_EM);
        editor.putString("CORR_EM", CORR_EM);

        token.setText(TOKEN);
        companyid.setText(COMPANY_ID);
        id_em.setText(ID_EM);
        nom_em.setText(NOM_EM);
        corr_em.setText(CORR_EM);

        editor.commit();
    }

    //Aqui cargamos el token y el company_id gurdados en la memoria del celular
    private void cargarPreferencias() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String TOKEN = preferences.getString("TOKEN", "No Existe");
        String COMPANY_ID = preferences.getString("COMPANY_ID", "No Existe");
        String ID_EM = preferences.getString("ID_EM", "No Existe");
        String NOM_EM = preferences.getString("NOM_EM", "No Existe");
        String CORR_EM = preferences.getString("CORR_EM", "No Existe");
    }
}
