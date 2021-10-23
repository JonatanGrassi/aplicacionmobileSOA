package com.example.aplicacionsoa.presenter;

import org.json.JSONObject;

public interface MvpLogin_Registro {

    interface View {
        public void mostrarResultadoConexion(String cod);
    }
    interface Presenter
    {
        public void iniciarServicio(JSONObject obj);
        public void configurarBroadCastReciever();
        public void liberarRecursos();
        public boolean comprobarConexion();
        public void obtenerTokens(String token,String token_refresh);
        public void comunicarRespuestaExitosa();
        public void comunicarRespuestaFallida(String msj);
    }
}