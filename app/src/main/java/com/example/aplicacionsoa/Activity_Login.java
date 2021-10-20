package com.example.aplicacionsoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity_Login extends AppCompatActivity {

    private EditText ingresoMail;
    private EditText ingresoPass;
    private Button botonRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ingresoMail = (EditText) findViewById(R.id.editTextMailLog);
        ingresoPass = (EditText) findViewById(R.id.editTextPassWord);
        botonRegistrar = (Button) findViewById(R.id.button);
        botonRegistrar.setOnClickListener(HandlerLogin);
    }



    private View.OnClickListener HandlerLogin = (V) ->
    {
        Intent newIntent = new Intent(this, Activity_Register.class);
        newIntent.putExtra("descripcion",ingresoMail.getText().toString());
        startActivity(newIntent);
    };
}