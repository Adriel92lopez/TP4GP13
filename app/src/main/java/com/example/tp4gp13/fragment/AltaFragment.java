package com.example.tp4gp13.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp4gp13.R;
import com.example.tp4gp13.conexion.DataAltaActivity;
import com.example.tp4gp13.entidad.Articulo;

public class AltaFragment extends Fragment {

    private EditText etId;
    private EditText etNombre;
    private EditText etStock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alta, container, false);
        etId = view.findViewById(R.id.input_id);
        etNombre = view.findViewById(R.id.input_nombre_producto);
        etStock = view.findViewById(R.id.input_stock);
        Button btnAlta = view.findViewById(R.id.btn_agregar);

        btnAlta.setOnClickListener(v -> darDeAlta());

        return view;
    }

    private void darDeAlta() {
        String id = etId.getText().toString();
        String nombre = etNombre.getText().toString();
        String stock = etStock.getText().toString();

        if (id.isEmpty() || nombre.isEmpty() || stock.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Articulo nuevoArticulo = new Articulo();
        nuevoArticulo.setId(Integer.parseInt(id));
        nuevoArticulo.setNombre(nombre);
        nuevoArticulo.setStock(Integer.parseInt(stock));

        DataAltaActivity dataAltaActivity = new DataAltaActivity(getActivity(), nuevoArticulo);
        dataAltaActivity.setDataAltaListener(new DataAltaActivity.DataAltaListener() {
            @Override
            public void onAltaExitosa() {
                Toast.makeText(getContext(), "Artículo Agregado exitosamente", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            }

            @Override
            public void onAltaFallida(String error) {
                Toast.makeText(getContext(), "Error al Agregar Articulo: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void limpiarCampos() {
        etNombre.setText("");
        etStock.setText("");
    }
}