package com.example.appsetas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//desactiva volver atras de accion.



        Intent intent = getIntent();
        String user = intent.getStringExtra("user");

        // Solo guardar si el user no es null
        if (user != null) {
            SharedPreferences sharedPreferences = getSharedPreferences("UserPf", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("user", user);
            editor.apply(); // Asegúrate de que se aplica correctamente
        }

        // Leer el valor almacenado
        SharedPreferences sharedPreferences = getSharedPreferences("UserPf", MODE_PRIVATE);
        String userName = sharedPreferences.getString("user", "Usuario por defecto"); // El valor por defecto

        TextView cajatexto = findViewById(R.id.txtbienvenido);
        cajatexto.setText("¡BIENVENIDO " + userName + "!");

        Button boton1 = findViewById(R.id.btnmain);
        if (savedInstanceState == null) {
            cargarFragmento(new AgrandarLetraFragment());
        }

        View.OnClickListener lst = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(MainActivity.this, primera.class);
                startActivity(intent2);

            }
        }; boton1.setOnClickListener(lst);

    }
    // Infla el menú de opciones (el ícono de configuración)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_agranar_letra) {
            cargarFragmento(new AgrandarLetraFragment());
            return true;
        } else if (id == R.id.action_modificar_datos) {
            cargarFragmento(new ModificarUsuarioFragment());
            return true;
        } else if (id == R.id.action_eliminar_cuenta) {
            cargarFragmento(new EliminarUsuarioFragment());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void cargarFragmento(androidx.fragment.app.Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment); // R.id.fragmentContainer es el contenedor en el layout
        transaction.addToBackStack(null); // Permite volver atrás al fragmento anterior
        transaction.commit();
    }
    public void ajustarTamañoLetra(float incrementPercentage) {
        // Recorrer todas las vistas de la actividad y ajustar el tamaño de los TextViews
        ajustarTamañoLetraEnVista(findViewById(android.R.id.content), incrementPercentage);
    }

    private void ajustarTamañoLetraEnVista(View view, float incrementPercentage) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;

            // Obtener el tamaño actual de la fuente (en píxeles)
            float currentTextSize = textView.getTextSize();

            // Calcular el nuevo tamaño de la fuente
            float newTextSize = currentTextSize * incrementPercentage;
            textView.setTextSize(newTextSize / getResources().getDisplayMetrics().scaledDensity); // Convertir a sp

        }

        // Si la vista tiene hijos (es un ViewGroup), recorramos sus hijos
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                ajustarTamañoLetraEnVista(viewGroup.getChildAt(i), incrementPercentage);
            }
        }
    }
}



