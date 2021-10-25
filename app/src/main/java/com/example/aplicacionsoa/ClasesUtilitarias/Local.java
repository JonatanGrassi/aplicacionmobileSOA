package com.example.aplicacionsoa.ClasesUtilitarias;

import java.util.ArrayList;

public abstract class Local {
    protected String tipoDeLocal;
    protected Integer porcetajeAforo;
    protected ArrayList<String> recomendaciones;

    public Local(String tipoDeLocal,Integer porcetajeAforo)
    {
        this.tipoDeLocal = tipoDeLocal;
        this.porcetajeAforo = porcetajeAforo;
        //this.recomendaciones = recomendaciones;
    }

    public void setRecomendaciones(ArrayList<String> recomendaciones)
    {
        this.recomendaciones = recomendaciones;
    }

    public abstract Integer calcularAforo(Double metrosCuadrados);

    public ArrayList<String> getRecomendaciones()
    {
        return this.recomendaciones;
    }

    public String getTipoDeLocal()
    {
        return this.tipoDeLocal;
    }
}
