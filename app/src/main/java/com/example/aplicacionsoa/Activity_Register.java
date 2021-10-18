package com.example.aplicacionsoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_Register extends AppCompatActivity {
    private EditText nombre;
    private EditText apellido;
    private EditText dni;
    private EditText mail;
    private EditText ambiente;
    private EditText comision;
    private EditText grupo;
    private EditText contraseña;
    private Button botonRegistrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nombre = (EditText) findViewById(R.id.editTextNombre);
        apellido = (EditText) findViewById(R.id.editTextApellido);
        mail = (EditText) findViewById(R.id.editTextEmail);
        dni = (EditText) findViewById(R.id.editTextDNI);
        ambiente = (EditText) findViewById(R.id.editTextAmbiente);
        comision = (EditText) findViewById(R.id.editTextComision);
        grupo = (EditText) findViewById(R.id.editTextGrupo);
        contraseña = (EditText) findViewById(R.id.editTextPassWord);
        botonRegistrar = (Button) findViewById(R.id.buttonRegistrar);
    }
}