package com.example.appsetas;

import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;

import java.sql.SQLOutput;

public class Final extends AppCompatActivity {
    TextView txtFinal;
    View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        txtFinal = findViewById(R.id.txtfinal);
        ImageView imageSeta = findViewById(R.id.imagenseta);
        Button btnConsultar = findViewById(R.id.btnconsulta);
        rootView = findViewById(android.R.id.content); // Obtén la raíz de la vista actual

        String[] toxicas = {
                "Boletus Satanas",
                "Omphalotus Olearius",
                "Paxillus Involutus",
                "Clitocybe Dealbata",
                "Clitocybe Phillophila",
                "Tricoloma Pardinum",
                "Entoloma Nidorosum",
                "Amanita Muscaria",
                "Amanita Pantherina",
                "Agricus Xanthoderma"
        };
        String[] mortales = {
                "Gyromitra Esculenta",
                "Amanita Phalloides",
                "Amanita Verna",
                "Amanita Virosa",
                "Lepiota Helveola",
                "Cortinarius Orellanus",
                "Cortinarius Splendens",
                "Galerina Marginata"
        };

        Intent intent = getIntent();

        String tamano_sombrero = intent.getStringExtra("tamanosombrero");
        String color_sombrero = intent.getStringExtra("colorsombrero");
        String motasEscamas = intent.getStringExtra("motasEscamas");
        String tipo_laminas = intent.getStringExtra("laminas");
        String esporada = intent.getStringExtra("esporada");
        String separacion = intent.getStringExtra("separacion");
        String color_laminas = intent.getStringExtra("colorLaminas");

        String tamaño_pie = intent.getStringExtra("tipoPie");
        String color_pie = intent.getStringExtra("colorPie");
        String rompe = intent.getStringExtra("rompe");
        String anillo = intent.getStringExtra("anillo");
        String volva = intent.getStringExtra("volva");
        String escamas = intent.getStringExtra("escamas");

        String habitat = intent.getStringExtra("habitat");
        String temporada = intent.getStringExtra("temporada");
        String tamano_seta = intent.getStringExtra("tamano");
        String tipo="Boletal";


        SharedPreferences sharedPreferences = getSharedPreferences("sptoken", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);

        String resultado = "Confusión posible: \n\n";
        resultado += "Habitat: " + habitat + "\n";
        resultado += "Temporada: " + temporada + "\n";
        resultado += "Tamaño: " + tamano_seta + "\n\n";
        resultado += "Detalles de la identificación: \n";
        resultado += "Tipo de Pie: " + tamaño_pie + "\n";
        resultado += "Color del Pie: " + color_pie + "\n";
        resultado += "Rompe: " + rompe + "\n";
        resultado += "Anillo: " + anillo + "\n";
        resultado += "Volva: " + volva + "\n";
        resultado += "Escamas: " + escamas + "\n\n";
        resultado += "Token: " + token;

        //txtFinal.setText(resultado);
        System.out.println("resultado es"+ resultado);

        View.OnClickListener lstfinal = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarConsulta(tipo, habitat, temporada, tamano_seta,tamaño_pie, color_pie, anillo, volva, tamano_sombrero,color_sombrero,color_laminas,tipo_laminas,esporada,token, toxicas, mortales);
            }
        };
        btnConsultar.setOnClickListener(lstfinal);

        Button btnvolver = findViewById(R.id.btnvolverfinal);
        btnvolver.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent1);
            finish();
        });
    }

    public void realizarConsulta(String tipo, String habitat, String temporada, String tamano_seta, String tamaño_pie,
                                 String color_pie, String anillo, String volva, String tamano_sombrero,
                                 String color_sombrero, String color_laminas, String tipo_laminas,
                                 String esporada, String token, String[] toxicas, String[] mortales) {
        // Construir la URL de consulta con los parámetros proporcionados
        String url = "http://10.0.2.2:4030/comprobar_seta?"
                + "token=" + Uri.encode(token)
                + "&tipo=" + Uri.encode(tipo)
                + "&habitat=" + Uri.encode(habitat)
                + "&temporada=" + Uri.encode(temporada)
                + "&tamaño_seta=" + Uri.encode(tamano_seta)
                + "&tamaño_pie=" + Uri.encode(tamaño_pie)
                + "&color_pie=" + Uri.encode(color_pie)
                + "&anillo=" + Uri.encode(anillo)
                + "&volva=" + Uri.encode(volva)
                + "&tamaño_sombrero=" + Uri.encode(tamano_sombrero)
                + "&color_sombrero=" + Uri.encode(color_sombrero)
                + "&color_laminas=" + Uri.encode(color_laminas)
                + "&esporada=" + Uri.encode(esporada)
                + "&tipo_laminas=" + Uri.encode(tipo_laminas);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                // Parsear la respuesta como JSON Array
                JSONArray jsonArray = new JSONArray(response);

                // Iterar sobre los elementos de la respuesta
                for (int i = 0; i < jsonArray.length(); i++) {
                    String seta = jsonArray.getString(i);

                    // Verificar si la seta está en los arrays de tóxicas o mortales
                    if (isInArray(seta, toxicas)) {
                        // Cambiar el fondo a seta_vomito
                        rootView.setBackgroundResource(R.drawable.calavera);
                        Toast.makeText(Final.this, "Seta tóxica detectada: " + seta, Toast.LENGTH_LONG).show();
                    } else if (isInArray(seta, mortales)) {
                        // Cambiar el fondo a seta_calavera
                        rootView.setBackgroundResource(R.drawable.calavera);
                        Toast.makeText(Final.this, "Seta mortal detectada: " + seta, Toast.LENGTH_LONG).show();
                    }
                }

                // Mostrar la respuesta completa en el TextView
                txtFinal.setText(response);

            } catch (JSONException e) {
                Toast.makeText(Final.this, "Error al procesar la respuesta: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }, error -> Toast.makeText(Final.this, "Error en la solicitud: " + error.getMessage(), Toast.LENGTH_LONG).show());

        Volley.newRequestQueue(this).add(stringRequest);
    }

    // Método para verificar si un elemento está en un array
    private boolean isInArray(String value, String[] array) {
        for (String item : array) {
            if (item.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}

