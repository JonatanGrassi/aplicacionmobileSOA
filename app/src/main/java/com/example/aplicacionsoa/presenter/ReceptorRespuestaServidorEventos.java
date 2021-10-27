package com.example.aplicacionsoa.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReceptorRespuestaServidorEventos extends BroadcastReceiver {

    private MvpGestionarIngresos.Presenter presenter;

    public ReceptorRespuestaServidorEventos(MvpGestionarIngresos.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean success = intent.getExtras().getBoolean("success");
        presenter.desregistrarBroadcastEventos();
        if(!success)
        {
            String msjError = intent.getExtras().getString("msjError");
            if(msjError.equals("tiempo de sesion expirado."))
                presenter.falloTokenExpirado();
        }
        else
            presenter.registracionExitosa();
    }
}
