package com.example.aplicacionsoa.model;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.example.aplicacionsoa.presenter.PresenterRegistro;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class Http_Conection_Service extends IntentService {

    private HttpURLConnection connection;
    private URL url;

    public Http_Conection_Service() {
        super("Http_Conection_Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String uri = intent.getExtras().getString("URI");
        try {
            JSONObject obj = new JSONObject(intent.getExtras().getString("JSON"));
            int result = realizarPOST(obj,uri);
            ejecutarPOST(result);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int realizarPOST(JSONObject obj,String uri)
    {
        connection = null;
        String result = null;
        int respuesta = -1;
        try {
            url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json ; charset=UTF-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(10000);
            DataOutputStream dots = new DataOutputStream(connection.getOutputStream());
            dots.write(obj.toString().getBytes(StandardCharsets.UTF_8));
            dots.flush();
            connection.connect();
            respuesta = connection.getResponseCode();
            if(respuesta == HttpURLConnection.HTTP_OK || respuesta == HttpURLConnection.HTTP_CREATED)
            {
                InputStreamReader iSr = new InputStreamReader(connection.getInputStream());
                result=convertInputStreamToString(iSr).toString();
            }
            else if (respuesta == HttpURLConnection.HTTP_BAD_REQUEST)
            {
                InputStreamReader iSr = new InputStreamReader(connection.getInputStream());
                result=convertInputStreamToString(iSr).toString();
            }
            dots.close();
            connection.disconnect();
            return respuesta;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return respuesta;

    }

    public void ejecutarPOST(int result)
    {
        if(result!=-1)
        {

        }
        Intent i = new Intent(PresenterRegistro.ACTIONBROADCAST);
        i.putExtra("rtaServ",Integer.valueOf(result).toString());
        sendBroadcast(i);
    }

    private StringBuilder convertInputStreamToString(InputStreamReader inputStream) throws IOException {
        BufferedReader br = new BufferedReader(inputStream);
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            result.append(line + "\n");
        }
        br.close();
        return result;
    }
}