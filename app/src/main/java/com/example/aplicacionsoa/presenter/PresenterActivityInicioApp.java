package com.example.aplicacionsoa.presenter;

import android.content.Intent;

import com.example.aplicacionsoa.view.Activity_inicio_app;
import com.example.aplicacionsoa.view.activity_opciones_app;

public class PresenterActivityInicioApp implements MvpInicioApp.Presenter{
    private Activity_inicio_app view;
    private String eleccion;
    private String nombreLocal;
    private Double metrosCuadrados;


    public PresenterActivityInicioApp(Activity_inicio_app view)
    {
        this.view = view;
    }


    @Override
    public Boolean validarEntradas(boolean checkRestaurant, boolean checkBar, boolean checkDiscoteca, String nombreLocal,String metrosCuadrados) {
        eleccion="";
        boolean resultado=true;
        this.nombreLocal = nombreLocal;
        if(checkRestaurant)
            eleccion = "restaurant";
        else if(checkBar)
            eleccion = "bar";
        else if(checkDiscoteca)
            eleccion = "discoteca";

        if(eleccion.equals(""))
        {
            view.errorSeleccionDeLocal("debe seleccionar un tipo de local");
            resultado = false;
        }
        if(nombreLocal.isEmpty())
        {
            view.errorNombreLocal("debe ingresar un nombre del local");
            resultado = false;
        }
        if(metrosCuadrados.equals("") || metrosCuadrados.equals("Ingrese Metros cuadrados de su local") )
        {
            view.errorMetrosCuadrados("debe ingresar los metros cuadrados de su local");
            resultado = false;
        }
        else
            this.metrosCuadrados = Double.parseDouble(metrosCuadrados);
        return  resultado;
    }

    @Override
    public void cambiarActivity() {
        Intent newIntent = new Intent(view, activity_opciones_app.class);
        newIntent.putExtra("nombreLocal",this.nombreLocal);
        newIntent.putExtra("eleccion",this.eleccion);
        newIntent.putExtra("metrosCuadrados",this.metrosCuadrados);
        view.startActivity(newIntent);
    }
}
