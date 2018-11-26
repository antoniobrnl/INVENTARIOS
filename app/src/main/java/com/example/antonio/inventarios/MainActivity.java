package com.example.antonio.inventarios;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
                    PendientesFragment fragment = new PendientesFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.constraint, fragment, "FragmentName");
                    fragmentTransaction1.commit();
                    return true;
                case R.id.navigation_realizados:
                    //ACCIÓN
                    RealizadosFragment fragment2 = new RealizadosFragment();
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
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        PendientesFragment fragment = new PendientesFragment();
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
        AlertDialog.Builder myBuilder= new AlertDialog.Builder(this, R.style.AlertDialogStyle);
        final CharSequence[] opciones= {"Perfil","Ayuda","Cerrar Sesión"};
        AlertDialog.Builder builder = myBuilder.setTitle("Configuración").setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, final int position) {
                //PERFIL
                if (position == 0) {
                    AlertDialog.Builder myBuilder3 = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogStyle);
                    final CharSequence[] opciones = {"ID: 000000 ", "Nombre: Ejemplo Ejemplo ", "Correo: ejemplo@software.com", "Compañia: SofTeam", "Tipo: Empleado"};
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
                        String emp = "SofTeam";
                        final String tel = "8542678532";
                        final String corr = "software@software.com";
                        final String dir = "http://www.faccebook.com";

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
                    SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                    String TOKEN = "";
                    String COMPANY_ID = "";
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("TOKEN", TOKEN);
                    editor.putString("COMPANY_ID", COMPANY_ID);
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

