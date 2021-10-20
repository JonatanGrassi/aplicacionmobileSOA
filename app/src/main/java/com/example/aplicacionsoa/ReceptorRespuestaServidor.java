package com.example.aplicacionsoa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ReceptorRespuestaServidor extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String cod = intent.getExtras().getString("rtaServ");
    }
}
