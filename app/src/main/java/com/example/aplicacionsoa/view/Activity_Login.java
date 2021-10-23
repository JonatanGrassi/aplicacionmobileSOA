package com.example.aplicacionsoa.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacionsoa.R;
import com.example.aplicacionsoa.presenter.MvpLogin_Registro;
import com.example.aplicacionsoa.presenter.PresenterLogin;

import org.json.JSONObject;

public class Activity_Login extends AppCompatActivity implements MvpLogin_Registro.View {

    private EditText ingresoMail;
    private EditText ingresoPass;
    private TextView registrate;
    private Button botonIngresar;
    private PresenterLogin presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ingresoMail = (EditText) findViewById(R.id.editTextMailLog);
        ingresoPass = (EditText) findViewById(R.id.editTextPassWord);
        botonIngresar = (Button) findViewById(R.id.button);
        registrate = (TextView) findViewById(R.id.textViewRegistrate);
        botonIngresar.setOnClickListener(HandlerLogin);
        registrate.setOnClickListener(HandlerRegistrate);
        presenter = new PresenterLogin(this);
        presenter.configurarBroadCastReciever();
    }

    @Override
    protected void onDestroy() {
        presenter.liberarRecursos();
        super.onDestroy();
    }

    private View.OnClickListener HandlerLogin = (V) ->
    {
        if(presenter.comprobarConexion())
        {
            JSONObject obj = presenter.getJsonObject(ingresoMail.getText().toString(),ingresoPass.getText().toString());
            presenter.iniciarServicio(obj);
        }
        else
        {
            Toast.makeText(this,"sin conexion a internet.No se podra Loguear",Toast.LENGTH_LONG).show();
        }
    };

    private View.OnClickListener HandlerRegistrate = (V) ->
    {
        Intent newIntent = new Intent(this, Activity_Register.class);
        startActivity(newIntent);
    };

    @Override
    public void mostrarResultadoConexion(String cod) {
        Toast.makeText(this,cod,Toast.LENGTH_LONG).show();
    }
}