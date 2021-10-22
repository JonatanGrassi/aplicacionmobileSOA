package com.example.aplicacionsoa.presenter;

import android.content.Intent;
import android.content.IntentFilter;

import androidx.annotation.NonNull;

import com.example.aplicacionsoa.view.Http_Conection_Service_POST;
import com.example.aplicacionsoa.Utilitarias;
import com.example.aplicacionsoa.view.Activity_Register;
import com.example.aplicacionsoa.view.Activity_inicio_app;

import org.json.JSONException;
import org.json.JSONObject;

public class PresenterRegistro implements Mvp.Presenter {
    private Activity_Register viewRegistro;

    ReceptorRespuestaServidor broadcast;
    public static final String ACTIONBROADCAST = "com.example.aplicacionsoa.presenter.intentfilter.RTA_SERVIDOR";
    private IntentFilter filtro;
    public static final String URI_REGISTER = "http://so-unlam.net.ar/api/api/register";
    private String token_refresh;
    private String token;

    public PresenterRegistro(Activity_Register viewRegistro) {
        this.viewRegistro = viewRegistro;
    }

    @Override
    public void iniciarServicio(JSONObject obj) {
        Intent reg = new Intent(viewRegistro, Http_Conection_Service_POST.class);
        reg.putExtra("URI",URI_REGISTER);
        reg.putExtra("JSON",obj.toString());
        viewRegistro.startService(reg);
    }

    @NonNull
    public JSONObject getJsonObject(String env, String nombre, String apellido, String mail, String dni, String contrasenia, String comision, String grupo) {
        JSONObject obj = null;
        try {
            obj = new JSONObject();
            obj.put("env", env);
            obj.put("name", nombre);
            obj.put("lastname", apellido);
            obj.put("dni", dni);
            obj.put("email", mail);
            obj.put("password", contrasenia);
            obj.put("commission", comision);
            obj.put("group", grupo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void configurarBroadCastReciever() {
        filtro = new IntentFilter(ACTIONBROADCAST);
        filtro.addCategory(Intent.CATEGORY_DEFAULT);
        broadcast = new ReceptorRespuestaServidor(this);
        viewRegistro.registerReceiver(broadcast,filtro);
    }

    @Override
    public void liberarRecursos() {
        if(Utilitarias.isMyServiceRunning(Http_Conection_Service_POST.class,viewRegistro)) {
            viewRegistro.stopService(new Intent(viewRegistro, Http_Conection_Service_POST.class));
        }
        if(broadcast!=null)
            viewRegistro.unregisterReceiver(broadcast);
    }

    @Override
    public boolean comprobarConexion() {
       return Utilitarias.comprobarConexion(viewRegistro);
    }

    @Override
    public void obtenerTokens(String token_refresh,String token ) {
        this.token=token;
        this.token_refresh=token_refresh;
    }


    @Override
    public void comunicarRespuestaExitosa() {
        viewRegistro.mostrarResultadoConexion("Se ha registrado correctamente");
        Intent newIntent = new Intent(viewRegistro, Activity_inicio_app.class);
        viewRegistro.startActivity(newIntent);
        liberarRecursos();
        //newIntent.putExtra("token_refresh",token_refresh);
        //newIntent.putExtra("token",token);

    }

    @Override
    public void comunicarRespuestaFallida(String msj) {
        viewRegistro.mostrarResultadoConexion(msj);
        liberarRecursos();
    }

}
