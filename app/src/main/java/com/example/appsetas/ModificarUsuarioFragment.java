package com.example.appsetas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ModificarUsuarioFragment extends Fragment {

    private EditText editTextNombre;
    private EditText editTextApellido;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        return inflater.inflate(R.layout.activity_modificar_usuario_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        editTextNombre = getView().findViewById(R.id.editTextUsuario);
        editTextApellido = getView().findViewById(R.id.editTextapellido);
        Button btnModificar = getView().findViewById(R.id.btnModificar);

        btnModificar.setOnClickListener(v -> modificarUsuario());
    }

    private void modificarUsuario() {
        String nuevoNombre = editTextNombre.getText().toString();
        String nuevoApellido = editTextApellido.getText().toString();
        if (nuevoNombre.isEmpty() && nuevoApellido.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingresa un nombre o un apellido", Toast.LENGTH_SHORT).show();
        } else {
            // Aquí podrías hacer la llamada a la API para modificar el usuario
            Toast.makeText(getActivity(), "Nombre de usuario modificado: " + nuevoNombre, Toast.LENGTH_SHORT).show();
        }
    }
}
