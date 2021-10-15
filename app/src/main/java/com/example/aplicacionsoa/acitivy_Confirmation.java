package com.example.aplicacionsoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.time.Instant;

public class acitivy_Confirmation extends AppCompatActivity {
    private TextView txtview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivy_confirmation);
        txtview1 = (TextView) findViewById(R.id.resultView2);
        Intent intent_d = getIntent();
        Bundle obj = intent_d.getExtras();
        txtview1.setText(obj.getString("descripcion"));
    }
}