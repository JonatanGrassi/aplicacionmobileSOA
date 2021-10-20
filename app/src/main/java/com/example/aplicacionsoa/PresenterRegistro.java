package com.example.aplicacionsoa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONObject;

import java.util.logging.Filter;

public class PresenterRegistro implements Registro.Presenter {
    private Activity_Register viewRegistro;
    private Registro.Model modelRegistro;
    ReceptorRespuestaServidor broadcast;
    public static final String ACTIONBROADCAST = "com.example.aplicacionsoa.intentfilter.RTA_SERVIDOR";
    private IntentFilter filtro;
    public static final String URI_REGISTER = "http://so-unlam.net.ar/api/api/register";

    public PresenterRegistro(Activity_Register viewRegistro) {
        this.viewRegistro = viewRegistro;
        modelRegistro = new modelRegistro(this);

    }

    @Override
    public void iniciarServicio(JSONObject obj) {
        Intent reg = new Intent(viewRegistro,Http_Conection_Service.class);
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
        viewRegistro.stopService(new Intent(viewRegistro, Http_Conection_Service.class));
        viewRegistro.unregisterReceiver(broadcast);
    }

    @Override
    public boolean comprarConexion() {
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
