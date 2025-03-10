package com.example.appsetas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class primera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera);

        TextView textDondeRecogisteSetas = findViewById(R.id.txt_recogida);

        CheckBox checkboxPradera = findViewById(R.id.checkbox_pradera);
        CheckBox checkboxBosque = findViewById(R.id.checkbox_bosque);
        CheckBox checkboxMatorral = findViewById(R.id.checkbox_matorral);

        TextView textTemporadaRecogida = findViewById(R.id.text_temporada_recogida);

        CheckBox checkboxPrimavera = findViewById(R.id.checkbox_primavera);
        CheckBox checkboxVerano = findViewById(R.id.checkbox_verano);
        CheckBox checkboxOtono = findViewById(R.id.checkbox_otono);
        CheckBox checkboxInvierno = findViewById(R.id.checkbox_invierno);

        TextView textTamanoSeta = findViewById(R.id.text_tamano_seta);

        CheckBox checkboxGrande = findViewById(R.id.checkbox_grande);
        CheckBox checkboxPequeno = findViewById(R.id.checkbox_pequeno);
        CheckBox checkboxMediano = findViewById(R.id.checkbox_mediano);



        // Configuración de los CheckBox para Hábitat (solo uno seleccionado)
        checkboxPradera.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxBosque.setChecked(false);
                checkboxMatorral.setChecked(false);
            }
        });
        checkboxBosque.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxPradera.setChecked(false);
                checkboxMatorral.setChecked(false);
            }
        });
        checkboxMatorral.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxPradera.setChecked(false);
                checkboxBosque.setChecked(false);
            }
        });

        // Configuración de los CheckBox para Temporada (solo uno seleccionado)
        checkboxPrimavera.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxVerano.setChecked(false);
                checkboxOtono.setChecked(false);
                checkboxInvierno.setChecked(false);
            }
        });
        checkboxVerano.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxPrimavera.setChecked(false);
                checkboxOtono.setChecked(false);
                checkboxInvierno.setChecked(false);
            }
        });
        checkboxOtono.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxPrimavera.setChecked(false);
                checkboxVerano.setChecked(false);
                checkboxInvierno.setChecked(false);
            }
        });
        checkboxInvierno.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxPrimavera.setChecked(false);
                checkboxVerano.setChecked(false);
                checkboxOtono.setChecked(false);
            }
        });

        // Configuración de los CheckBox para Tamaño (solo uno seleccionado)
        checkboxGrande.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxPequeno.setChecked(false);
                checkboxMediano.setChecked(false);
            }
        });
        checkboxPequeno.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxGrande.setChecked(false);
                checkboxMediano.setChecked(false);
            }
        });
        checkboxMediano.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxGrande.setChecked(false);
                checkboxPequeno.setChecked(false);
            }
        });

        Button btnprimera = findViewById(R.id.btnprimera);
        btnprimera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String habitatSeleccionado = "";
                String temporadaSeleccionada = "";
                String tamanoSeleccionado = "";

                if (checkboxPradera.isChecked()) {
                    habitatSeleccionado = "Pradera";
                } else if (checkboxBosque.isChecked()) {
                    habitatSeleccionado = "Bosque";
                } else if (checkboxMatorral.isChecked()) {
                    habitatSeleccionado = "Matorral";
                }

                if (checkboxPrimavera.isChecked()) {
                    temporadaSeleccionada = "Primavera";
                } else if (checkboxVerano.isChecked()) {
                    temporadaSeleccionada = "Verano";
                } else if (checkboxOtono.isChecked()) {
                    temporadaSeleccionada = "Otoño";
                } else if (checkboxInvierno.isChecked()) {
                    temporadaSeleccionada = "Invierno";
                }

                if (checkboxGrande.isChecked()) {
                    tamanoSeleccionado = "Grande";
                } else if (checkboxPequeno.isChecked()) {
                    tamanoSeleccionado = "Pequeño";
                } else if (checkboxMediano.isChecked()) {
                    tamanoSeleccionado = "Mediano";
                }

                if (habitatSeleccionado.isEmpty() || temporadaSeleccionada.isEmpty() || tamanoSeleccionado.isEmpty()) {
                    Toast.makeText(primera.this, "Por favor, selecciona una opción en cada grupo.", Toast.LENGTH_SHORT).show();
                } else {
                    String resultado = "Hábitat: " + habitatSeleccionado + "\n" +
                            "Temporada: " + temporadaSeleccionada + "\n" +
                            "Tamaño: " + tamanoSeleccionado;


                    Intent intentFinal = new Intent(primera.this, segundapie.class);
                    intentFinal.putExtra("habitat", habitatSeleccionado);
                    intentFinal.putExtra("temporada", temporadaSeleccionada);
                    intentFinal.putExtra("tamano", tamanoSeleccionado);

                    startActivity(intentFinal);

                }
            }
        });
        Button btnvolver = findViewById(R.id.btnvolver);
        View.OnClickListener lst = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };btnvolver.setOnClickListener(lst);
    }//fin oncreate
}




