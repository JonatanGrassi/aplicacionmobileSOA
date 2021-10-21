package com.example.aplicacionsoa.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.aplicacionsoa.model.Http_Conection_Service;
import com.example.aplicacionsoa.Utilitarias;
import com.example.aplicacionsoa.view.Activity_Register;

import org.json.JSONObject;

public class PresenterRegistro<inner> implements Registro.Presenter {
    private Activity_Register viewRegistro;

    ReceptorRespuestaServidor broadcast;
    public static final String ACTIONBROADCAST = "com.example.aplicacionsoa.presenter.intentfilter.RTA_SERVIDOR";
    private IntentFilter filtro;
    public static final String URI_REGISTER = "http://so-unlam.net.ar/api/api/register";

    public PresenterRegistro(Activity_Register viewRegistro) {
        this.viewRegistro = viewRegistro;
    }

    @Override
    public void iniciarServicio(JSONObject obj) {
        Intent reg = new Intent(viewRegistro, Http_Conection_Service.class);
        reg.putExtra("URI",URI_REGISTER);
        reg.putExtra("JSON",obj.toString());
        viewRegistro.startService(reg);
    }

    @Override
    public void configurarBroadCastReciever() {
        filtro = new IntentFilter(ACTIONBROADCAST);
        filtro.addCategory(Intent.CATEGORY_DEFAULT);
        broadcast = new ReceptorRespuestaServidor(viewRegistro);
        viewRegistro.registerReceiver(broadcast,filtro);
    }

    @Override
    public void liberarRecursos() {
        if(Utilitarias.isMyServiceRunning(Http_Conection_Service.class,viewRegistro)) {
            viewRegistro.stopService(new Intent(viewRegistro, Http_Conection_Service.class));
        }
        if(broadcast!=null)
            viewRegistro.unregisterReceiver(broadcast);
    }

    @Override
    public boolean comprobarConexion() {
       return Utilitarias.comprobarConexion(viewRegistro);
    }

    public class ReceptorRespuestaServidor extends BroadcastReceiver {
        private Activity_Register view;

        public ReceptorRespuestaServidor(Activity_Register view) {
            this.view = view;
        }
        @Override
        public void onReceive(Context context, Intent intent) {
            String cod = intent.getExtras().getString("rtaServ");
            view.mostrarResultadoConexion(cod);
        }
    }

}
