package com.example.aplicacionsoa.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.aplicacionsoa.R;
import com.example.aplicacionsoa.presenter.MvpInicioApp;
import com.example.aplicacionsoa.presenter.PresenterActivityInicioApp;
import com.example.aplicacionsoa.presenter.PresenterLogin;

import org.json.JSONObject;

public class Activity_inicio_app extends AppCompatActivity implements MvpInicioApp.View {

    private RadioButton radRestaurante;
    private EditText nombreLocal;
    private EditText metrosCuadrado;
    private RadioButton radBar;
    private RadioButton radDiscoteca;
    private Button botonAceptar;
    private MvpInicioApp.Presenter presenter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_app);
        nombreLocal = (EditText) findViewById(R.id.editTextNombreLocal);
        nombreLocal.setOnFocusChangeListener(SetOnFocusLocal);
        metrosCuadrado = (EditText) findViewById(R.id.editTextMetrosCuadrados);
        metrosCuadrado.setOnFocusChangeListener(SetOnFocusMetrosCuadrados);
        radRestaurante = (RadioButton) findViewById(R.id.radButtonRestaurante);
        radDiscoteca = (RadioButton) findViewById(R.id.radButtonDiscoteca);
        radBar = (RadioButton) findViewById(R.id.radButtonBar);
        botonAceptar = (Button) findViewById(R.id.buttonAceptar);
        botonAceptar.setOnClickListener(HandlerAceptar);
        presenter = new PresenterActivityInicioApp(this);
    }

    private View.OnClickListener HandlerAceptar = (V) ->
    {
       if(presenter.validarEntradas(radRestaurante.isChecked(),radBar.isChecked(),radDiscoteca.isChecked(),nombreLocal.getText().toString(),metrosCuadrado.getText().toString()))
       {
           presenter.cambiarActivity();
       }
    };

    private View.OnFocusChangeListener SetOnFocusLocal = (new View.OnFocusChangeListener()
    {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus)
        {
            nombreLocal.setText("");
        }
    }
    });

    private View.OnFocusChangeListener SetOnFocusMetrosCuadrados = (new View.OnFocusChangeListener()
    {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus)
            {
                metrosCuadrado.setText("");
            }
        }
    });


    @Override
    public void errorSeleccionDeLocal(String msj) {
        Toast.makeText(this,msj,Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorNombreLocal(String msj) {
        Toast.makeText(this,msj,Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorMetrosCuadrados(String msj) {
        Toast.makeText(this,msj,Toast.LENGTH_LONG).show();
    }
}