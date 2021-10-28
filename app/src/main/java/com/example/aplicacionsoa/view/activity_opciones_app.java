package com.example.aplicacionsoa.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacionsoa.R;
import com.example.aplicacionsoa.presenter.MvpOpcionesApp;
import com.example.aplicacionsoa.presenter.PresenterActivityOpcionesApp;

import org.json.JSONObject;

public class activity_opciones_app extends AppCompatActivity implements MvpOpcionesApp.View {
    private TextView nombreLocal;
    private Button botonGestionar;
    private Button botonConsultarTips;
    private Button botonConsultarAforo;
    private Button botonMostrarMetricas;
    private MvpOpcionesApp.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_app);
        botonConsultarAforo = (Button) findViewById(R.id.BottonConsultarAforo);
        botonConsultarTips = (Button) findViewById(R.id.buttonConsultarRecomendaciones);
        botonGestionar = (Button) findViewById(R.id.ButtonGestionarIngresos);
        nombreLocal = (TextView) findViewById(R.id.textViewNombreLocal);
        botonMostrarMetricas = (Button)findViewById(R.id.botonMostrarMetricas);
        botonConsultarAforo.setOnClickListener(HandlerConsultarAforo);
        botonConsultarTips.setOnClickListener(HandlerConsultarTips);
        botonGestionar.setOnClickListener(HandlerGestionar);
        botonMostrarMetricas.setOnClickListener(HandlerMostrarMetricas);

        presenter = new PresenterActivityOpcionesApp(this);
        presenter.obtenerNombreLocal();
    }

    private View.OnClickListener HandlerConsultarAforo = (V) ->
    {
        presenter.cambiarActivity(activity_consultar_aforo.class);
    };

    private View.OnClickListener HandlerConsultarTips = (V) ->
    {
        presenter.cambiarActivity(activity_consultar_recomendaciones.class);
    };

    private View.OnClickListener HandlerGestionar = (V) ->
    {
        presenter.cambiarActivity(activity_gestionarIngresos.class);
    };

    private View.OnClickListener HandlerMostrarMetricas = (V) -> {
        presenter.cambiarActivity(Activity_Metricas.class);
    };

    @Override
    public void setearNombreDeLocal(String nombre) {
        nombreLocal.setText(nombre);
    }
}