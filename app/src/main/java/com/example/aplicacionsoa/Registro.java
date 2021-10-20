package com.example.aplicacionsoa;

import org.json.JSONObject;

public interface Registro {

    interface View {
        public void mostrarConexionExitosa();
    }

    interface Presenter
    {
        public void iniciarServicio(JSONObject obj);
        public void configurarBroadCastReciever();
    }

    interface Model
    {

    }
}
