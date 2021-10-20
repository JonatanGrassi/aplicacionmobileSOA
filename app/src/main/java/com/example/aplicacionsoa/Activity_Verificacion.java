package com.example.aplicacionsoa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Verificacion extends AppCompatActivity {
    private EditText numeroText;
    private EditText codSeguridad;
    private Button confirmarCod;
    private Button enviarConfirmarCod;
    private String codigoDeConfirmacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion);
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
            codigoDeConfirmacion = obtenerCodigoVerif(9999,1000).toString();
            sms.sendTextMessage(numeroText.getText().toString(), null,codigoDeConfirmacion, null, null);

        });

        numeroText.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    numeroText.setText("");
                }
            }
        });

        codSeguridad.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    codSeguridad.setText("");
                }
            }
        });


        confirmarCod.setOnClickListener((V) ->
        {
            /*
            if(codSeguridad.getText().toString().equals(codigoDeConfirmacion))
            {
                Intent newIntent = new Intent(this, Activity_Login.class);
                startActivity(newIntent);
            }
            else
            {
                mostrarDialogoDeError();
            }
             */
            Intent newIntent = new Intent(this, Activity_Login.class);
            startActivity(newIntent);
     });
}

    public Integer obtenerCodigoVerif(int numMaximo,int numeroMinimo)
    {
        Double result = Math.floor(Math.random() * numMaximo + 1);
        if(result<=numeroMinimo)
            result += numeroMinimo;
        return result.intValue();
    }
    public void mostrarDialogoDeError()
    {
        AlertDialog.Builder aLd = new AlertDialog.Builder(this);
        aLd.setTitle("Codigo de verificacion erroneo"). setMessage("Ingrese el codigo nuevamente");
        aLd.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        aLd.show();
    }
}