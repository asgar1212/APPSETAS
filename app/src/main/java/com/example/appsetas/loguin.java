package com.example.appsetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class loguin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loguin);

        EditText cajauser = (EditText) this.findViewById(R.id.cajaloguinuser);
        EditText cajapass = (EditText) this.findViewById(R.id.cajaloguinpassword);
        Button btnacceder = (Button) this.findViewById(R.id.loginButton);
        TextView txtloguin = (TextView) this.findViewById(R.id.txtloguin);

        View.OnClickListener lstacceder =new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = cajauser.getText().toString();
                String password = cajapass.getText().toString();

                if (user.isEmpty() || password.isEmpty()) {
                    txtloguin.setText("Por favor completa todos los campos");
                    return;
                }
                String url = "http://10.0.2.2:4030/loguin?user=" + Uri.encode(user) + "&password=" + Uri.encode(password);

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String token = jsonResponse.optString("token", "");

                            if (!token.isEmpty()) {
                                SharedPreferences sharedPreferences = getSharedPreferences("sptoken", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("token", token);
                                editor.commit();

                                txtloguin.setText("Logueado con exito");


                                Intent intent = new Intent(loguin.this, MainActivity.class);
                                intent.putExtra("user",user);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(loguin.this, "No se recibió un token", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(loguin.this, "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtloguin.setText("El user y el loguin no coinciden");
                        Toast.makeText(loguin.this, "Error en la solicitud: El user y el loguin no coinciden " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                Volley.newRequestQueue(loguin.this).add(stringRequest);
            }//fin del onclick

        };btnacceder.setOnClickListener(lstacceder);
    }
}