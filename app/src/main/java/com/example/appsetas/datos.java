package com.example.appsetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class datos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        EditText cajanombre = (EditText) this.findViewById(R.id.cajanombre);
        EditText cajaapellido = (EditText) this.findViewById(R.id.cajaapellido);
        EditText cajauser = (EditText) this.findViewById(R.id.cajauser);
        EditText cajapass = (EditText) this.findViewById(R.id.cajapassword);
        Button btnacceder = (Button) this.findViewById(R.id.loginButton);
        Button btnloguin = (Button) this.findViewById(R.id.btnseta);



        View.OnClickListener lstacceder =new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //saco los campos de los editext
                String nombre = cajanombre.getText().toString();
                String apellido = cajaapellido.getText().toString();
                String user = cajauser.getText().toString();
                String password = cajapass.getText().toString();
                // Compruebo los campos
                if (nombre.isEmpty() || apellido.isEmpty() || user.isEmpty() || password.isEmpty()) {
                    Toast.makeText(datos.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crear el  JSON
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("nombre", nombre);
                    jsonObject.put("apellido", apellido);
                    jsonObject.put("user", user);
                    jsonObject.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Creo el  POST
                String url ="http://10.0.2.2:4030/agregar_usuario"; // Reemplaza con tu URL
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(datos.this, "Usuario agregado: " + response.toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(datos.this, loguin.class);
                        startActivity(intent);
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("11" + error.getMessage());
                        Toast.makeText(datos.this, "Error en la solicitud: El usuario ya existe " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                );

                // Añadir la solicitud a la cola de Volley
                Volley.newRequestQueue(datos.this).add(jsonObjectRequest);
            }//fin del onclick

        };btnacceder.setOnClickListener(lstacceder);

        View.OnClickListener listnerseta = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(datos.this, loguin.class);
                startActivity(intent2);
                finish();


            }
        };btnloguin.setOnClickListener(listnerseta);

    }
}