package com.example.aplicacionsoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText ingresoMail;
    private EditText ingresoPass;
    private Button botonRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ingresoMail = (EditText) findViewById(R.id.editTextMailLog);
        ingresoPass = (EditText) findViewById(R.id.editTextPassWord);
        botonRegistrar = (Button) findViewById(R.id.button);


        botonRegistrar.setOnClickListener((V) ->
        {
            Intent newIntent = new Intent(this,acitivy_Confirmation.class);
            newIntent.putExtra("descripcion",ingresoMail.getText().toString());
            startActivity(newIntent);
        });
    }
}