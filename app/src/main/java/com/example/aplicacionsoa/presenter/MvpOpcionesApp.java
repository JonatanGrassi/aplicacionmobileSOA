package com.example.aplicacionsoa.presenter;

import androidx.appcompat.app.AppCompatActivity;

public interface MvpOpcionesApp {

    interface View {
        public void setearNombreDeLocal(String nombre);
    }
    interface Presenter
    {
        public void cambiarActivity(Class<?> activity);
        public void obtenerNombreLocal();
    }
}
