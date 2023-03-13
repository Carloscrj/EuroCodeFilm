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

public class TaquillaFragment extends Fragment{


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


    ImageView ivTaquilla;

    LinearLayout llAsiento1;
    LinearLayout llAsiento2;
    LinearLayout llAsiento3;






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
        ivTaquilla = vista.findViewById(R.id.ivTaquilla);
        llAsiento1 = vista.findViewById(R.id.llAsiento1);
        llAsiento2 = vista.findViewById(R.id.llAsiento2);
        llAsiento3 = vista.findViewById(R.id.llAsiento3);

        llAsiento1.setVisibility(View.GONE);
        llAsiento2.setVisibility(View.GONE);
        llAsiento3.setVisibility(View.GONE);
        ivTaquilla.setVisibility(View.GONE);
        tvAgradecimiento.setVisibility(View.GONE);

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



        btnEntradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Entradas compradas", Toast.LENGTH_SHORT).show();
                tvTaquilla.setVisibility(View.GONE);
                tvTituloPelicula.setVisibility(View.GONE);
                tvSinopsisPelicula.setVisibility(View.GONE);
                tvSalaPelicula.setVisibility(View.GONE);
                tvButacas.setVisibility(View.GONE);
                tvNumEntradas.setVisibility(View.GONE);
                tvPrecio.setVisibility(View.GONE);
                spnEntradas.setVisibility(View.GONE);
                tvReprePrecio.setVisibility(View.GONE);
                tvRepreTitulo.setVisibility(View.GONE);
                tvRepreSinopsis.setVisibility(View.GONE);
                tvRepreSala.setVisibility(View.GONE);
                btnEntradas.setVisibility(View.GONE);

                ivTaquilla.setVisibility(View.VISIBLE);
                tvAgradecimiento.setVisibility(View.VISIBLE);
            }
        });

        return vista;
    }


}