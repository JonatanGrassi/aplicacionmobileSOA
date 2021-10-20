package com.example.aplicacionsoa;

import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

public class UsuarioJSON {

    public JSONObject crearObjetoJSON(String env,String nombre,String apellido,String mail,String dni,String contraseña,String comision,String grupo)
    {
        JSONObject obj = null;
        try {
            obj = new JSONObject();
            obj.put("env",env);
            obj.put("name",nombre);
            obj.put("lastname",apellido);
            obj.put("dni",dni);
            obj.put("email",mail);
            obj.put("password",contraseña);
            obj.put("commission",comision);
            obj.put("group",grupo);
            return obj;
        } catch (JSONException e) {
            e.printStackTrace();
            return obj;
        }
    }

}