package com.example.appsetas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

    public class AgrandarLetraFragment extends Fragment {

        private float incrementPercentage = 1.2f; // Incremento de 20%

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflar el layout del fragmento
            return inflater.inflate(R.layout.activity_agrandar_letra_fragment, container, false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            // Aquí puedes agregar un botón o algún control para ejecutar el agrandamiento de la letra
            Toast.makeText(getActivity(), "Fragmento para Agrandar Letra", Toast.LENGTH_SHORT).show();
        }

        // Este método es llamado desde la actividad principal para agrandar las letras
        public void agrandarLetra() {
            MainActivity activity = (MainActivity) getActivity();
            if (activity != null) {
                // Se le pasa el incremento al MainActivity para que ajuste el tamaño de las letras
                activity.ajustarTamañoLetra(incrementPercentage);
            }
        }
    }


