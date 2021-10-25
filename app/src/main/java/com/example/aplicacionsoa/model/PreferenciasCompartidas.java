package com.example.aplicacionsoa.model;

import android.app.Activity;
import android.content.SharedPreferences;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class PreferenciasCompartidas {
    private SharedPreferences preferences;

    public PreferenciasCompartidas(Activity activity, String key) {
        preferences = activity.getApplicationContext().getSharedPreferences(key,activity.MODE_PRIVATE);
    }

    public void guardarSesionFallida() {
        Calendar calendario = new GregorianCalendar();
        SharedPreferences.Editor editor = preferences.edit();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        String key = null;
        if(hora<6)
            key = "CANTIDAD_INICIOS_SESION_FALLIDOS_MADRUGADA";
        if(hora>=6 && hora<12)
            key = "CANTIDAD_INICIOS_SESION_FALLIDOS_MAÑANA";
        if(hora>=12 && hora<18)
            key = "CANTIDAD_INICIOS_SESION_FALLIDOS_TARDE";
        if(hora>=18)
             key = "CANTIDAD_INICIOS_SESION_FALLIDOS_NOCHE";
        int cantidadIniciosFallidos = preferences.getInt(key,0);
        cantidadIniciosFallidos++;
        editor.putInt(key,cantidadIniciosFallidos);
        editor.commit();
    }

    public void guardarRegistro(){
        Calendar calendario = new GregorianCalendar();
        SharedPreferences.Editor editor = preferences.edit();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        String key = hora < 12 ? "CANTIDAD_REGISTROS_AM" : "CANTIDAD_REGISTROS_PM";
        int cantRegistros = preferences.getInt(key,0);
        cantRegistros++;
        editor.putInt(key,cantRegistros);
        editor.commit();
    }
}


