package com.example.appsetas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class EliminarUsuarioFragment extends Fragment {
    private String user;
    private String token;
    TextView txteliminar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_action_eliminar_usuario, container, false);
        view.setBackgroundResource(R.drawable.boletus);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        EditText cajatexto = getView().findViewById(R.id.cajaeliminarcuenta);
        txteliminar = getView().findViewById(R.id.txteliminar);  // No lo usas en el código, pero lo puedes eliminar si no es necesario
        Button btnEliminar = getView().findViewById(R.id.btnEliminar);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sptoken", getActivity().MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);
        user = cajatexto.getText().toString();

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = cajatexto.getText().toString();

                if (token == null || user.isEmpty()) {
                    txteliminar.setText("Token o nombre de usuario vacío");
                    Toast.makeText(getActivity(), "Token o nombre de usuario vacío", Toast.LENGTH_SHORT).show();
                    return;
                }

                eliminarUsuario(user, token);
            }
        });
    }

    private void eliminarUsuario(String user, String token) {

        String url = "http://10.0.2.2:4030/eliminar_usuarios?"
                + "token=" + Uri.encode(token)
                + "&user=" + Uri.encode(user);

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                txteliminar.setText("Eliminado con exito: ");
                Toast.makeText(getActivity(), "Eliminado con exito: " + response, Toast.LENGTH_LONG).show();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sptoken", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // Redirigir a la actividad de inicio de sesión
                Intent intent = new Intent(getActivity(), loguin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Error en la solicitud: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) ;

        // Agregar la solicitud a la cola de Volley
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }
}



