package com.example.aplicacionsoa.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicacionsoa.R;

public class Activity_Verificacion extends AppCompatActivity {
    private EditText numeroText;
    private EditText codSeguridad;
    private Button confirmarCod;
    private Button enviarConfirmarCod;
    private String codigoDeConfirmacion;
    private int nivelDeBateria;
    private int escalaBateria;
    private Integer porcentajeBateria;
    private int minCodigoVerificacion = 1000;
    private int maxCodigoVerificacion = 9999;
    private boolean mensajeEnviado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion);
        TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        numeroText = (EditText) findViewById(R.id.PlainTextIngresoNum);
        codSeguridad = (EditText) findViewById(R.id.textPlainIngresCod);
        codSeguridad.setEnabled(false);
        confirmarCod = (Button) findViewById(R.id.confirmarCod);
        confirmarCod.setEnabled(false);
        enviarConfirmarCod = (Button) findViewById(R.id.enviarCodigoSeg);

        IntentFilter intentFilterBateria = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent estadoBateria = getApplicationContext().registerReceiver(null,intentFilterBateria);
        nivelDeBateria = estadoBateria.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        escalaBateria = estadoBateria.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        porcentajeBateria = nivelDeBateria * 100 / escalaBateria;
        Toast.makeText(getApplicationContext(), "Bateria: "+porcentajeBateria.toString()+"%", Toast.LENGTH_LONG).show();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] { Manifest.permission.SEND_SMS}, 1);
        }

        enviarConfirmarCod.setOnClickListener((V) ->
        {
            String telefono = numeroText.getText().toString();
            if(telefono.isEmpty()) {
                Toast.makeText(this, "Debe ingresar un numero de telefono", Toast.LENGTH_SHORT).show();
            }else {
                SmsManager sms = SmsManager.getDefault();
                codigoDeConfirmacion = obtenerCodigoVerif(maxCodigoVerificacion, minCodigoVerificacion).toString();
                sms.sendTextMessage(telefono, null, codigoDeConfirmacion, null, null);
                Toast.makeText(this,"Codigo enviado, revise su mensajeria",Toast.LENGTH_SHORT).show();
                codSeguridad.setEnabled(true);
                confirmarCod.setEnabled(true);
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
                //mostrarDialogoDeError();
                Toast.makeText(this,"Codigo de verificacion erroneo",Toast.LENGTH_LONG).show();
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