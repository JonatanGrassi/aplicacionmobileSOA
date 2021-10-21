package com.example.aplicacionsoa.presenter;

import org.json.JSONObject;

public interface Registro {

    interface View {
        public void mostrarResultadoConexion(String cod);
    }

    interface Presenter
    {
        public void iniciarServicio(JSONObject obj);
        public void configurarBroadCastReciever();
        public void liberarRecursos();
        public boolean comprobarConexion();
    }
}
