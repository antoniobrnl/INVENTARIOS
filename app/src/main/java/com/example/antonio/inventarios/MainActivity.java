package com.example.antonio.inventarios;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import static com.example.antonio.inventarios.R.menu.menu_main;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    AlertDialog myDialog;
    AlertDialog myDialog2;
    AlertDialog myDialog3;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_pendientes:
                    //ACCIÓN
                    PendientesFragment fragment = new PendientesFragment(getApplicationContext());
                    android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.constraint, fragment, "FragmentName");
                    fragmentTransaction1.commit();
                    return true;
                case R.id.navigation_realizados:
                    //ACCIÓN
                    RealizadosFragment fragment2 = new RealizadosFragment(getApplicationContext());
                    android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.constraint, fragment2, "FragmentName");
                    fragmentTransaction2.commit();
                    return true;
                case R.id.navigation_inventarios:
                    //ACCIÓN
                    InventariosFragment fragment3 = new InventariosFragment(getApplicationContext());
                    android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.constraint, fragment3, "FragmentName");
                    fragmentTransaction3.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100:
                //acción o método a realizar.
                break;
        }
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String TOKEN = preferences.getString("TOKEN","No Existe");

        if (TOKEN == ""){
            setContentView(R.layout.activity_main);
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }else {
            setContentView(R.layout.activity_main);
        }
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sistema de Inventarios");
        PendientesFragment fragment = new PendientesFragment(getApplicationContext());
        android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.constraint, fragment, "FragmentName");
        fragmentTransaction1.commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate((menu_main),menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.action_config){
        }
        showAlert();
        return super.onOptionsItemSelected(item);
    }

    //Boton retroceder deshabilitado
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showAlert(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String COMPANY_ID = preferences.getString("COMPANY_ID", "No Existe");
        final String ID_EM = preferences.getString("ID_EM", "No Existe");
        final String NOM_EM = preferences.getString("NOM_EM", "No Existe");
        final String CORR_EM = preferences.getString("CORR_EM", "No Existe");
        AlertDialog.Builder myBuilder= new AlertDialog.Builder(this, R.style.AlertDialogStyle);
        final CharSequence[] opciones= {"Perfil","Ayuda","Cerrar Sesión"};
        AlertDialog.Builder builder = myBuilder.setTitle("Configuración").setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, final int position) {
                //PERFIL
                if (position == 0) {
                    AlertDialog.Builder myBuilder3 = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogStyle);
                    final CharSequence[] opciones = {"ID: "+ID_EM, "Nombre: "+NOM_EM, "Correo: "+CORR_EM, "Compañia: "+COMPANY_ID};
                    myBuilder3.setTitle("Perfil").setItems(opciones, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    myDialog3 = myBuilder3.create();
                    myDialog3.show();
                }
                //AYUDA
                if (position == 1) {
                        String emp = "DeveloperSofTeam";
                        final String tel = "6621536060";
                        final String corr = "developersofteam@gmail.com";
                        final String dir = "https://app-inventar.herokuapp.com/";

                        AlertDialog.Builder myBuilder2 = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogStyle);
                        final CharSequence[] opciones = {"Empresa Desarrolladora: "+emp, "Telefono: "+tel, "Correo: "+corr, "Preguntas"};
                        myBuilder2.setTitle("Ayuda").setItems(opciones, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int pos) {
                                if (pos==1) {
                                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                    ClipData clip = ClipData.newPlainText("text", tel);
                                    clipboard.setPrimaryClip(clip);
                                    Toast.makeText(getApplicationContext(), "Copiado al portapapeles: " + tel, Toast.LENGTH_SHORT).show();
                                }
                                if (pos==2){
                                        ClipboardManager clipboard2 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                        ClipData clip2 = ClipData.newPlainText("text",  corr);
                                        clipboard2.setPrimaryClip(clip2);
                                        Toast.makeText(getApplicationContext(),"Copiado al portapapeles: "+corr, Toast.LENGTH_SHORT).show();
                                }
                                if (pos==3){
                                    Uri uriUrl = Uri.parse(dir);
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uriUrl);
                                    startActivity(intent);
                                }
                            }
                        });
                        myDialog2 = myBuilder2.create();
                        myDialog2.show();
                    }
                //CERRAR SESIÓN
                if (position == 2) {
                    //Vaciar toke y company_id
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String TOKEN = "";
                    String COMPANY_ID = "";
                    String ID_EM = "";
                    String NOM_EM = "";
                    String CORR_EM = "";
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("TOKEN", TOKEN);
                    editor.putString("COMPANY_ID", COMPANY_ID);
                    editor.putString("ID_EM", ID_EM);
                    editor.putString("NOM_EM", NOM_EM);
                    editor.putString("CORR_EM", CORR_EM);
                    editor.commit();
                    //Iniciamos LoginActivity
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    //Mensaje de finalizacion
                    Toast.makeText(MainActivity.this, "Sesión: Finalizada", Toast.LENGTH_SHORT).show();
                }
            }
        });
        myDialog = myBuilder.create();
        myDialog.show();
    }
}

