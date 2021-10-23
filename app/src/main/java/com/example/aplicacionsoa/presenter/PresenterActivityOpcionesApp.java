package com.example.aplicacionsoa.presenter;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacionsoa.view.Activity_inicio_app;
import com.example.aplicacionsoa.view.activity_opciones_app;

public class PresenterActivityOpcionesApp implements MvpOpcionesApp.Presenter{
    private String tipoLocal;
    private Double metrosCuadrados;
    private String nombreLocal;
    private activity_opciones_app view;

    public PresenterActivityOpcionesApp(activity_opciones_app view) {
        this.view = view;
    }

    @Override
    public void cambiarActivity(Class<?> activity) {
        Intent newIntent = new Intent(view,activity);
        newIntent.putExtra("metrosCuadrados",metrosCuadrados);
        newIntent.putExtra("tipoLocal",tipoLocal);
        newIntent.putExtra("nombreLocal",nombreLocal);
        view.startActivity(newIntent);
    }

    @Override
    public void obtenerNombreLocal() {
        metrosCuadrados = view.getIntent().getExtras().getDouble("metrosCuadrados");
        tipoLocal = view.getIntent().getExtras().getString("eleccion");
        nombreLocal = view.getIntent().getExtras().getString("nombreLocal");
        view.setearNombreDeLocal(nombreLocal);
    }
}
