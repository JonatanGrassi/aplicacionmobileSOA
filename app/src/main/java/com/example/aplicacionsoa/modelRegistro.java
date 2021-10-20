package com.example.aplicacionsoa;

public class modelRegistro implements Registro.Model {
    private Registro.Presenter presenter;

    public modelRegistro(Registro.Presenter presenter) {
        this.presenter = presenter;
    }
}
