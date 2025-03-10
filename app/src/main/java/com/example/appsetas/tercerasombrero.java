package com.example.appsetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class tercerasombrero extends AppCompatActivity {
    // Declarar todos los CheckBox que vamos a usar
    // Declarar todos los CheckBox que vamos a usar
    CheckBox checkboxGrande, checkboxMediano, checkboxPequeno;
    CheckBox checkboxBlanco, checkboxVerde, checkboxAmarillo, checkboxGris, checkboxMarron;
    CheckBox checkboxPalido, checkboxNegro, checkboxRojo, checkboxNaranja;
    CheckBox checkboxLaminas, checkboxPoros, checkboxPelillos;
    CheckBox checkboxSiEsporada, checkboxNoEsporada;
    CheckBox checkboxAdheridas, checkboxSueltos, checkboxFree;
    CheckBox checkboxMotas_Escamas_si,checkboxMotas_Escamas_no;;
    CheckBox checkboxColorLaminasBlanco, checkboxColorLaminasNegro, checkboxColorLaminasRojo,
            checkboxColorLaminasVerde, checkboxColorLaminasAzul, checkboxColorLaminasAmarillo,
            checkboxColorLaminasGris, checkboxColorLaminasMarron, checkboxColorLaminasNaranja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercerasombrero);

        // Inicializar los CheckBox
        checkboxGrande = findViewById(R.id.checkbox_grande);
        checkboxMediano = findViewById(R.id.checkbox_mediano);
        checkboxPequeno = findViewById(R.id.checkbox_pequeno);

        checkboxBlanco = findViewById(R.id.checkbox_blanco);
        checkboxVerde = findViewById(R.id.checkbox_verde);
        checkboxAmarillo = findViewById(R.id.checkbox_amarillo);
        checkboxGris = findViewById(R.id.checkbox_gris);
        checkboxMarron = findViewById(R.id.checkbox_marron);
        checkboxPalido = findViewById(R.id.checkbox_palido);
        checkboxNegro = findViewById(R.id.checkbox_negro);
        checkboxRojo = findViewById(R.id.checkbox_rojo);
        checkboxNaranja = findViewById(R.id.checkbox_naranja);

        checkboxLaminas = findViewById(R.id.checkbox_laminas);
        checkboxPoros = findViewById(R.id.checkbox_poros);
        checkboxPelillos = findViewById(R.id.checkbox_pelillos);

        checkboxSiEsporada = findViewById(R.id.checkbox_si_esporada);
        checkboxNoEsporada = findViewById(R.id.checkbox_no_esporada);

        checkboxAdheridas = findViewById(R.id.checkbox_adheridas);
        checkboxSueltos = findViewById(R.id.checkbox_sueltos);
        checkboxFree = findViewById(R.id.checkbox_free);

        // Motas y Escamas
        checkboxMotas_Escamas_si = findViewById(R.id.checkbox_si_motas_escamas);
        checkboxMotas_Escamas_no = findViewById(R.id.checkbox_no_motas_escamas);

        // Colores de las láminas
        checkboxColorLaminasBlanco = findViewById(R.id.checkbox_lamina_blanco);
        checkboxColorLaminasNegro = findViewById(R.id.checkbox_lamina_negro);
        checkboxColorLaminasRojo = findViewById(R.id.checkbox_lamina_rojo);
        checkboxColorLaminasVerde = findViewById(R.id.checkbox_lamina_verde);
        checkboxColorLaminasAzul = findViewById(R.id.checkbox_lamina_palido);
        checkboxColorLaminasAmarillo = findViewById(R.id.checkbox_lamina_amarillo);
        checkboxColorLaminasGris = findViewById(R.id.checkbox_lamina_gris);
        checkboxColorLaminasMarron = findViewById(R.id.checkbox_lamina_marron);
        checkboxColorLaminasNaranja = findViewById(R.id.checkbox_lamina_naranja);


        Button btnsegunda =(Button) findViewById(R.id.btntercera);
        Intent intent = getIntent();

        String habitat=intent.getStringExtra("habitat");
        String temporada=intent.getStringExtra("temporada");
        String tamano=intent.getStringExtra("tamano");
        String tipoPie=intent.getStringExtra("tipoPie");
        String colorPie=intent.getStringExtra("colorPie");
        String rompe=intent.getStringExtra("rompe");
        String anillo=intent.getStringExtra("anillo");
        String volva=intent.getStringExtra("volva");
        String escamas=intent.getStringExtra("escamas");

        View.OnClickListener lsttercera = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener las selecciones de los CheckBox para "Tamaño"
                String tamanosombrero = "";
                if (checkboxGrande.isChecked()) tamanosombrero = "Grande";
                else if (checkboxMediano.isChecked()) tamanosombrero = "Mediano";
                else if (checkboxPequeno.isChecked()) tamanosombrero = "Pequeño";

                // Obtener las selecciones de los CheckBox para "Color"
                String colorsombrero = "";
                if (checkboxBlanco.isChecked()) colorsombrero = "Blanco";
                else if (checkboxVerde.isChecked()) colorsombrero = "Verde";
                else if (checkboxAmarillo.isChecked()) colorsombrero = "Amarillo";
                else if (checkboxGris.isChecked()) colorsombrero = "Gris";
                else if (checkboxMarron.isChecked()) colorsombrero = "Marrón";
                else if (checkboxPalido.isChecked()) colorsombrero = "Pálido";
                else if (checkboxNegro.isChecked()) colorsombrero = "Negro";
                else if (checkboxRojo.isChecked()) colorsombrero = "Rojo";
                else if (checkboxNaranja.isChecked()) colorsombrero = "Naranja";

                // Obtener las selecciones de "Motas y Escamas"
                String motasEscamas = checkboxMotas_Escamas_si.isChecked() ? "Sí" : (checkboxMotas_Escamas_no.isChecked() ? "No": "");

                // Obtener las selecciones de "Láminas"
                String laminas = "";
                if (checkboxLaminas.isChecked()) laminas = "Láminas";
                else if (checkboxPoros.isChecked()) laminas = "Poros";
                else if (checkboxPelillos.isChecked()) laminas = "Pelillos";

                // Obtener la opción de "Esporada"
                String esporada = checkboxSiEsporada.isChecked() ? "Sí" : (checkboxNoEsporada.isChecked() ? "No" : "");

                // Obtener la selección de "Separación"
                String separacion = "";
                if (checkboxAdheridas.isChecked()) separacion = "Adheridas";
                else if (checkboxSueltos.isChecked()) separacion = "Sueltos";
                else if (checkboxFree.isChecked()) separacion = "Free";

                // Obtener el color de las láminas
                String colorLaminas = "";
                if (checkboxColorLaminasBlanco.isChecked()) colorLaminas = "Blanco";
                else if (checkboxColorLaminasNegro.isChecked()) colorLaminas = "Negro";
                else if (checkboxColorLaminasRojo.isChecked()) colorLaminas = "Rojo";
                else if (checkboxColorLaminasVerde.isChecked()) colorLaminas = "Verde";
                else if (checkboxColorLaminasAzul.isChecked()) colorLaminas = "Azul";
                else if (checkboxColorLaminasAmarillo.isChecked()) colorLaminas = "Amarillo";
                else if (checkboxColorLaminasGris.isChecked()) colorLaminas = "Gris";
                else if (checkboxColorLaminasMarron.isChecked()) colorLaminas = "Marrón";
                else if (checkboxColorLaminasNaranja.isChecked()) colorLaminas = "Naranja";

               Intent intent= new Intent(tercerasombrero.this,Final.class);
                intent.putExtra("tamanosombrero", tamanosombrero);
                intent.putExtra("colorsombrero", colorsombrero);
                intent.putExtra("motasEscamas", motasEscamas);
                intent.putExtra("laminas", laminas);
                intent.putExtra("esporada", esporada);
                intent.putExtra("separacion", separacion);
                intent.putExtra("colorLaminas", colorLaminas);

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
        };btnsegunda.setOnClickListener(lsttercera);

        Button btnvolver = findViewById(R.id.btnvolvertercera);
        View.OnClickListener lst = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };btnvolver.setOnClickListener(lst);
    }
}