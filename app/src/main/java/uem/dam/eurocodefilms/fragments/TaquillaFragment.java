package uem.dam.eurocodefilms.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import uem.dam.eurocodefilms.R;

public class TaquillaFragment extends Fragment implements View.OnClickListener{


    private String pelicula;
    private String sinopsis;
    private String sala;

    TextView tvTaquilla;

    TextView tvTituloPelicula;
    TextView tvRepreTitulo;

    TextView tvSinopsisPelicula;
    TextView tvRepreSinopsis;

    TextView tvSalaPelicula;
    TextView tvRepreSala;

    TextView tvNumEntradas;

    TextView tvPrecio;

    Spinner spnEntradas;

    TextView tvReprePrecio;

    TextView tvAgradecimiento;

    TextView tvButacas;

    Button btnEntradas;

    Button btnEligeAsientos;

    ImageView ivMapaCine;
    ImageView ivTaquilla;

    LinearLayout llAsiento1;
    LinearLayout llAsiento2;
    LinearLayout llAsiento3;
    LinearLayout llAsiento4;
    LinearLayout llAsiento5;
    LinearLayout llAsiento6;
    LinearLayout llAsiento7;
    LinearLayout llAsiento8;
    LinearLayout llAsiento9;

    public TaquillaFragment() {
        // Required empty public constructor
    }

    public static TaquillaFragment newInstance(String pelicula, String sinopsis, String sala) {
        TaquillaFragment fragment = new TaquillaFragment();
        Bundle args = new Bundle();
        args.putString(CarteleraFragment.CLAVE_PELICULA, pelicula);
        args.putString(CarteleraFragment.CLAVE_SINOPSIS, sinopsis);
        args.putString(CarteleraFragment.CLAVE_SALA, sala);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pelicula = getArguments().getString(CarteleraFragment.CLAVE_PELICULA);
            sinopsis = getArguments().getString(CarteleraFragment.CLAVE_SINOPSIS);
            sala = getArguments().getString(CarteleraFragment.CLAVE_SALA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_taquilla, container, false);

        tvTaquilla = vista.findViewById(R.id.tvTaquilla);
        tvTituloPelicula = vista.findViewById(R.id.tvTituloPelicula);
        tvSinopsisPelicula = vista.findViewById(R.id.tvSinopsisPelicula);
        tvSalaPelicula = vista.findViewById(R.id.tvSalaPelicula);
        tvButacas = vista.findViewById(R.id.tvButacas);
        tvNumEntradas = vista.findViewById(R.id.tvNumEntradas);
        tvPrecio = vista.findViewById(R.id.tvPrecio);
        spnEntradas = vista.findViewById(R.id.spnEntradas);
        tvReprePrecio = vista.findViewById(R.id.tvReprePrecio);
        tvRepreTitulo = vista.findViewById(R.id.tvRepreTitulo);
        tvRepreSinopsis = vista.findViewById(R.id.tvRepreSinopsis);
        tvRepreSala = vista.findViewById(R.id.tvRepreSala);
        tvAgradecimiento = vista.findViewById(R.id.tvAgradecimiento);
        btnEntradas = vista.findViewById(R.id.btnEntradas);
        btnEntradas.setOnClickListener(this);
        btnEligeAsientos = vista.findViewById(R.id.btnEligeAsientos);
        btnEligeAsientos.setOnClickListener(this);
        ivMapaCine = vista.findViewById(R.id.ivMapaCine);
        ivTaquilla = vista.findViewById(R.id.ivTaquilla);

        llAsiento1 = vista.findViewById(R.id.llAsiento1);
        llAsiento2 = vista.findViewById(R.id.llAsiento2);
        llAsiento3 = vista.findViewById(R.id.llAsiento3);
        llAsiento4 = vista.findViewById(R.id.llAsiento4);
        llAsiento5 = vista.findViewById(R.id.llAsiento5);
        llAsiento6 = vista.findViewById(R.id.llAsiento6);
        llAsiento7 = vista.findViewById(R.id.llAsiento7);
        llAsiento8 = vista.findViewById(R.id.llAsiento8);
        llAsiento9 = vista.findViewById(R.id.llAsiento9);

        ivTaquilla.setVisibility(View.GONE);
        tvAgradecimiento.setVisibility(View.GONE);
        llAsiento1.setVisibility(View.GONE);
        llAsiento2.setVisibility(View.GONE);
        llAsiento3.setVisibility(View.GONE);
        llAsiento4.setVisibility(View.GONE);
        llAsiento5.setVisibility(View.GONE);
        llAsiento6.setVisibility(View.GONE);
        llAsiento7.setVisibility(View.GONE);
        llAsiento8.setVisibility(View.GONE);
        llAsiento9.setVisibility(View.GONE);

        tvRepreTitulo.setText(pelicula);
        tvRepreSinopsis.setText(sinopsis);
        tvRepreSala.setText(sala);

        spnEntradas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int numEntradas = Integer.parseInt(spnEntradas.getSelectedItem().toString());
                tvReprePrecio.setText(String.valueOf(numEntradas * 8.60));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return vista;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnEntradas) {
            comprarEntradas();
        } else if (v.getId() == R.id.btnEligeAsientos) {
            seleccionAsientos();
        }
    }

    private void comprarEntradas() {
        Toast.makeText(getContext(), R.string.compra_hecha, Toast.LENGTH_SHORT).show();
        View[] views = {tvTaquilla, tvTituloPelicula, tvSinopsisPelicula, tvSalaPelicula, tvButacas,
                tvNumEntradas, tvPrecio, spnEntradas, tvReprePrecio, tvRepreTitulo,
                tvRepreSinopsis, tvRepreSala, btnEntradas, llAsiento1, llAsiento2,
                llAsiento3, llAsiento4, llAsiento5, llAsiento6, llAsiento7, llAsiento8,
                llAsiento9, btnEligeAsientos, ivMapaCine};
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
        ivTaquilla.setVisibility(View.VISIBLE);
        tvAgradecimiento.setVisibility(View.VISIBLE);
    }

    private void seleccionAsientos() {
        int numEntradas = Integer.parseInt(spnEntradas.getSelectedItem().toString());
        LinearLayout[] asientos = {llAsiento1, llAsiento2, llAsiento3, llAsiento4, llAsiento5,
                llAsiento6, llAsiento7, llAsiento8, llAsiento9};
        for (int i = 0; i < numEntradas; i++) {
            asientos[i].setVisibility(View.VISIBLE);
        }
    }
}