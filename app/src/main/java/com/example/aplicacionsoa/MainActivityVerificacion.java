package com.example.aplicacionsoa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivityVerificacion extends AppCompatActivity {
    private EditText numeroText;
    private EditText codSeguridad;
    private Button confirmarCod;
    private Button enviarConfirmarCod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_verificacion);
        TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        numeroText = (EditText) findViewById(R.id.PlainTextIngresoNum);
        codSeguridad = (EditText) findViewById(R.id.textPlainIngresCod);
        confirmarCod = (Button) findViewById(R.id.confirmarCod);
        enviarConfirmarCod = (Button) findViewById(R.id.enviarCodigoSeg);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] { Manifest.permission.SEND_SMS}, 1);
        }

        enviarConfirmarCod.setOnClickListener((V) ->
        {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numeroText.getText().toString(), null, "1234", null, null);
        });

        confirmarCod.setOnClickListener((V) ->
        {
            if(codSeguridad.getText().toString() == "1234")
            {
                return;
            }
        });




    }
}