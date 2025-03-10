package com.example.appsetas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class segundapie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundapie);

        // Definir los CheckBoxes para el tipo de pie
        CheckBox checkboxGrandePie = findViewById(R.id.checkbox_grande_pie);
        CheckBox checkboxMedianoPie = findViewById(R.id.checkbox_mediano_pie);
        CheckBox checkboxPequenoPie = findViewById(R.id.checkbox_pequeno_pie);

        // Definir los CheckBoxes para el color del pie
        CheckBox checkboxBlancoPie = findViewById(R.id.checkbox_blanco_pie);
        CheckBox checkboxVerdePie = findViewById(R.id.checkbox_verde_pie);
        CheckBox checkboxAmarilloPie = findViewById(R.id.checkbox_amarillo_pie);
        CheckBox checkboxNaranjaPie = findViewById(R.id.checkbox_naranja_pie);
        CheckBox checkboxRojoPie = findViewById(R.id.checkbox_rojo_pie);
        CheckBox checkboxMarronPie = findViewById(R.id.checkbox_marron_pie);
        CheckBox checkboxGrisPie = findViewById(R.id.checkbox_gris_pie);
        CheckBox checkboxNegroPie = findViewById(R.id.checkbox_negro_pie);

        // Definir los CheckBoxes para características del pie
        CheckBox checkboxRompe = findViewById(R.id.checkbox_rompe);
        CheckBox checkboxAnillo = findViewById(R.id.checkbox_anillo);
        CheckBox checkboxVolva = findViewById(R.id.checkbox_volva);
        CheckBox checkboxEscamas = findViewById(R.id.checkbox_escamas);

        // Configuración de los CheckBox para el tipo de pie (solo uno seleccionado)
        checkboxGrandePie.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxMedianoPie.setChecked(false);
                checkboxPequenoPie.setChecked(false);
            }
        });
        checkboxMedianoPie.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxGrandePie.setChecked(false);
                checkboxPequenoPie.setChecked(false);
            }
        });
        checkboxPequenoPie.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxGrandePie.setChecked(false);
                checkboxMedianoPie.setChecked(false);
            }
        });

        // Configuración de los CheckBox para el color de pie (solo uno seleccionado)
        checkboxBlancoPie.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxVerdePie.setChecked(false);
                checkboxAmarilloPie.setChecked(false);
                checkboxNaranjaPie.setChecked(false);
                checkboxRojoPie.setChecked(false);
                checkboxMarronPie.setChecked(false);
                checkboxGrisPie.setChecked(false);
                checkboxNegroPie.setChecked(false);
            }
        });
        checkboxVerdePie.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxBlancoPie.setChecked(false);
                checkboxAmarilloPie.setChecked(false);
                checkboxNaranjaPie.setChecked(false);
                checkboxRojoPie.setChecked(false);
                checkboxMarronPie.setChecked(false);
                checkboxGrisPie.setChecked(false);
                checkboxNegroPie.setChecked(false);
            }
        });
        checkboxAmarilloPie.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxBlancoPie.setChecked(false);
                checkboxVerdePie.setChecked(false);
                checkboxNaranjaPie.setChecked(false);
                checkboxRojoPie.setChecked(false);
                checkboxMarronPie.setChecked(false);
                checkboxGrisPie.setChecked(false);
                checkboxNegroPie.setChecked(false);
            }
        });
        checkboxNaranjaPie.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxBlancoPie.setChecked(false);
                checkboxVerdePie.setChecked(false);
                checkboxAmarilloPie.setChecked(false);
                checkboxRojoPie.setChecked(false);
                checkboxMarronPie.setChecked(false);
                checkboxGrisPie.setChecked(false);
                checkboxNegroPie.setChecked(false);
            }
        });
        checkboxRojoPie.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxBlancoPie.setChecked(false);
                checkboxVerdePie.setChecked(false);
                checkboxAmarilloPie.setChecked(false);
                checkboxNaranjaPie.setChecked(false);
                checkboxMarronPie.setChecked(false);
                checkboxGrisPie.setChecked(false);
                checkboxNegroPie.setChecked(false);
            }
        });
        checkboxMarronPie.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxBlancoPie.setChecked(false);
                checkboxVerdePie.setChecked(false);
                checkboxAmarilloPie.setChecked(false);
                checkboxNaranjaPie.setChecked(false);
                checkboxRojoPie.setChecked(false);
                checkboxGrisPie.setChecked(false);
                checkboxNegroPie.setChecked(false);
            }
        });
        checkboxGrisPie.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxBlancoPie.setChecked(false);
                checkboxVerdePie.setChecked(false);
                checkboxAmarilloPie.setChecked(false);
                checkboxNaranjaPie.setChecked(false);
                checkboxRojoPie.setChecked(false);
                checkboxMarronPie.setChecked(false);
                checkboxNegroPie.setChecked(false);
            }
        });
        checkboxNegroPie.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxBlancoPie.setChecked(false);
                checkboxVerdePie.setChecked(false);
                checkboxAmarilloPie.setChecked(false);
                checkboxNaranjaPie.setChecked(false);
                checkboxRojoPie.setChecked(false);
                checkboxMarronPie.setChecked(false);
                checkboxGrisPie.setChecked(false);
            }
        });

        // Configuración para que solo uno de las características del pie se pueda seleccionar
        checkboxRompe.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxAnillo.setChecked(false);
                checkboxVolva.setChecked(false);
                checkboxEscamas.setChecked(false);
            }
        });
        checkboxAnillo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxRompe.setChecked(false);
                checkboxVolva.setChecked(false);
                checkboxEscamas.setChecked(false);
            }
        });
        checkboxVolva.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxRompe.setChecked(false);
                checkboxAnillo.setChecked(false);
                checkboxEscamas.setChecked(false);
            }
        });
        checkboxEscamas.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxRompe.setChecked(false);
                checkboxAnillo.setChecked(false);
                checkboxVolva.setChecked(false);
            }
        });

        Button btnsegunda = findViewById(R.id.btnpsegunda);
        btnsegunda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recoger el tipo de pie seleccionado
                String tipoPie = "";
                if (checkboxGrandePie.isChecked()) tipoPie = "Grande";
                else if (checkboxMedianoPie.isChecked()) tipoPie = "Mediano";
                else if (checkboxPequenoPie.isChecked()) tipoPie = "Pequeño";

                // Recoger el color del pie seleccionado
                String colorPie = "";
                if (checkboxBlancoPie.isChecked()) colorPie = "Blanco";
                else if (checkboxVerdePie.isChecked()) colorPie = "Verde";
                else if (checkboxAmarilloPie.isChecked()) colorPie = "Amarillo";
                else if (checkboxNaranjaPie.isChecked()) colorPie = "Naranja";
                else if (checkboxRojoPie.isChecked()) colorPie = "Rojo";
                else if (checkboxMarronPie.isChecked()) colorPie = "Marrón";
                else if (checkboxGrisPie.isChecked()) colorPie = "Gris";
                else if (checkboxNegroPie.isChecked()) colorPie = "Negro";

                // Recoger las características del pie seleccionadas
                String rompe = checkboxRompe.isChecked() ? "Sí" : "No";
                String anillo = checkboxAnillo.isChecked() ? "Sí" : "No";
                String volva = checkboxVolva.isChecked() ? "Sí" : "No";
                String escamas = checkboxEscamas.isChecked() ? "Sí" : "No";

                // Crear el resultado que se mostrará
                String resultado = "Tipo de pie: " + tipoPie +
                        "\nColor de pie: " + colorPie +
                        "\nRompe: " + rompe +
                        "\nAnillo: " + anillo +
                        "\nVolva: " + volva +
                        "\nEscamas: " + escamas;


                Intent intent = getIntent();
                String habitat=intent.getStringExtra("habitat");
                String temporada=intent.getStringExtra("temporada");
                String tamano=intent.getStringExtra("tamano");
                //Toast.makeText(segundapie.this, resultado, Toast.LENGTH_SHORT).show();

                intent = new Intent(segundapie.this, tercerasombrero.class);

                intent.putExtra("habitat", habitat);
                intent.putExtra("temporada", temporada);
                intent.putExtra("tamano", tamano);
                intent.putExtra("tipoPie", tipoPie);
                intent.putExtra("colorPie", colorPie);
                intent.putExtra("rompe", rompe);
                intent.putExtra("anillo", anillo);
                intent.putExtra("volva", volva);
                intent.putExtra("escamas", escamas);
                startActivity(intent);
                finish();


            }
        });


        Button btnvolver = findViewById(R.id.btnvolversegunda);
        View.OnClickListener lst = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };btnvolver.setOnClickListener(lst);
    }
}