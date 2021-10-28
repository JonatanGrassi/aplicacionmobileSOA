package com.example.aplicacionsoa.model;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;



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
            key = "Inicios de sesion fallidos madrugada (00-06)hs";
        if(hora>=6 && hora<12)
            key = "Inicios de sesion fallidos mañana (06-12)hs";
        if(hora>=12 && hora<18)
            key = "Inicios de sesion fallidos tarde (12-18)hs";
        if(hora>=18)
             key = "Inicios de sesion fallidos noche (18-00)hs";
        int cantidadIniciosFallidos = preferences.getInt(key,0);
        cantidadIniciosFallidos++;
        editor.putInt(key,cantidadIniciosFallidos);
        editor.commit();
    }

    public void guardarRegistro(){
        Calendar calendario = new GregorianCalendar();
        SharedPreferences.Editor editor = preferences.edit();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        String key = hora < 12 ? "Cantidad registros en AM (00-12)hs" : "Cantidad registros en PM (12-00)hs";
        int cantRegistros = preferences.getInt(key,0);
        cantRegistros++;
        editor.putInt(key,cantRegistros);
        editor.commit();
    }

    public Map<String,?> obtenerMetricas(){
        Map<String, ?> entradas = preferences.getAll();
        return entradas;
    }
}


