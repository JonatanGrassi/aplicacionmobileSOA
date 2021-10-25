package com.example.aplicacionsoa.ClasesUtilitarias;

import java.util.ArrayList;

public class Discoteca extends Local{

    public Discoteca() {
        super("discoteca", 50);
        ArrayList<String> recom = new ArrayList<>();
        super.setRecomendaciones(recom);
    }

    @Override
    public Integer calcularAforo(Double metrosCuadrados) {
        return null;
    }
}
