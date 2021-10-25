package com.example.aplicacionsoa.ClasesUtilitarias;

import java.util.ArrayList;

public class Restaurant extends Local {

    public Restaurant() {
        super("restaurant", 100);
        ArrayList<String> recom = new ArrayList<>();
        super.setRecomendaciones(recom);
    }

    @Override
    public Integer calcularAforo(Double metrosCuadrados) {
        return null;
    }
}
