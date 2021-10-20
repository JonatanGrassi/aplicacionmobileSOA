package com.example.aplicacionsoa;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

import org.json.JSONObject;

import java.util.logging.Filter;

public class PresenterRegistro implements Registro.Presenter {
    private Activity_Register viewRegistro;
    private Registro.Model modelRegistro;
    private ReceptorRespuestaServidor broadcast;
    public static final String ACTIONBROADCAST = "RESPUESTA_SERVIDOR";
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
        viewRegistro.registerReceiver(broadcast,filtro);
    }
}
