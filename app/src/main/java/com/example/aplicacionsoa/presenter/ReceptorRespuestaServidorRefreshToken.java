package com.example.aplicacionsoa.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReceptorRespuestaServidorRefreshToken extends BroadcastReceiver {
    private PresenterRegistro presenter;

    public ReceptorRespuestaServidorRefreshToken(PresenterRegistro presenter) {
        this.presenter = presenter;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean success = intent.getExtras().getBoolean("success");
        if(success)
        {
            String token_refresh = intent.getExtras().getString("token_refresh");
            String token = intent.getExtras().getString("token");
            presenter.actualizarTokens(token_refresh,token);
        }
        /*
        else
        {
            String msjError = intent.getExtras().getString("msjError");

        }
        */

    }
}
