package com.example.aplicacionsoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class Activity_Register extends AppCompatActivity implements Registro.View {
    private EditText nombre;
    private EditText apellido;
    private EditText dni;
    private EditText mail;
    private EditText ambiente;
    private EditText comision;
    private EditText grupo;
    private EditText contraseña;
    private Button enviarInformacionDeRegistro;
    private Registro.Presenter presenter;


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
        enviarInformacionDeRegistro = (Button) findViewById(R.id.buttonRegistrar);
        enviarInformacionDeRegistro.setOnClickListener(HandlerRegistro);
        presenter = new PresenterRegistro(this);
        presenter.configurarBroadCastReciever();
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(Activity_Register.this, Http_Conection_Service.class));
        super.onDestroy();
    }

    private View.OnClickListener HandlerRegistro = (V) ->
    {
        UsuarioJSON UsuJSON = new UsuarioJSON();
        JSONObject obj = UsuJSON.crearObjetoJSON(ambiente.getText().toString(),nombre.getText().toString(),apellido.getText().toString()
        ,mail.getText().toString(),dni.getText().toString(),contraseña.getText().toString(),comision.getText().toString()
        ,grupo.getText().toString());
        presenter.iniciarServicio(obj);
    };


    @Override
    public void mostrarConexionExitosa() {

    }
}