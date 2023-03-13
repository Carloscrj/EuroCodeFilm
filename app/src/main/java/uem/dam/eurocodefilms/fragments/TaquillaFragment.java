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
        btnEligeAsientos = vista.findViewById(R.id.btnEligeAsientos);
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

        btnEligeAsientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spnEntradas.getSelectedItem().toString().equals("1")){
                    llAsiento1.setVisibility(View.VISIBLE);
                }else if (spnEntradas.getSelectedItem().toString().equals("2")){
                    llAsiento1.setVisibility(View.VISIBLE);
                    llAsiento2.setVisibility(View.VISIBLE);
                }else if (spnEntradas.getSelectedItem().toString().equals("3")){
                    llAsiento1.setVisibility(View.VISIBLE);
                    llAsiento2.setVisibility(View.VISIBLE);
                    llAsiento3.setVisibility(View.VISIBLE);
                }else if (spnEntradas.getSelectedItem().toString().equals("4")){
                    llAsiento1.setVisibility(View.VISIBLE);
                    llAsiento2.setVisibility(View.VISIBLE);
                    llAsiento3.setVisibility(View.VISIBLE);
                    llAsiento4.setVisibility(View.VISIBLE);
                }else if (spnEntradas.getSelectedItem().toString().equals("5")){
                    llAsiento1.setVisibility(View.VISIBLE);
                    llAsiento2.setVisibility(View.VISIBLE);
                    llAsiento3.setVisibility(View.VISIBLE);
                    llAsiento4.setVisibility(View.VISIBLE);
                    llAsiento5.setVisibility(View.VISIBLE);
                }else if (spnEntradas.getSelectedItem().toString().equals("6")){
                    llAsiento1.setVisibility(View.VISIBLE);
                    llAsiento2.setVisibility(View.VISIBLE);
                    llAsiento3.setVisibility(View.VISIBLE);
                    llAsiento4.setVisibility(View.VISIBLE);
                    llAsiento5.setVisibility(View.VISIBLE);
                    llAsiento6.setVisibility(View.VISIBLE);
                }else if (spnEntradas.getSelectedItem().toString().equals("7")){
                    llAsiento1.setVisibility(View.VISIBLE);
                    llAsiento2.setVisibility(View.VISIBLE);
                    llAsiento3.setVisibility(View.VISIBLE);
                    llAsiento4.setVisibility(View.VISIBLE);
                    llAsiento5.setVisibility(View.VISIBLE);
                    llAsiento6.setVisibility(View.VISIBLE);
                    llAsiento7.setVisibility(View.VISIBLE);
                }else if (spnEntradas.getSelectedItem().toString().equals("8")){
                    llAsiento1.setVisibility(View.VISIBLE);
                    llAsiento2.setVisibility(View.VISIBLE);
                    llAsiento3.setVisibility(View.VISIBLE);
                    llAsiento4.setVisibility(View.VISIBLE);
                    llAsiento5.setVisibility(View.VISIBLE);
                    llAsiento6.setVisibility(View.VISIBLE);
                    llAsiento7.setVisibility(View.VISIBLE);
                    llAsiento8.setVisibility(View.VISIBLE);
                }else if (spnEntradas.getSelectedItem().toString().equals("9")){
                    llAsiento1.setVisibility(View.VISIBLE);
                    llAsiento2.setVisibility(View.VISIBLE);
                    llAsiento3.setVisibility(View.VISIBLE);
                    llAsiento4.setVisibility(View.VISIBLE);
                    llAsiento5.setVisibility(View.VISIBLE);
                    llAsiento6.setVisibility(View.VISIBLE);
                    llAsiento7.setVisibility(View.VISIBLE);
                    llAsiento8.setVisibility(View.VISIBLE);
                    llAsiento9.setVisibility(View.VISIBLE);
                }
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
                llAsiento1.setVisibility(View.GONE);
                llAsiento2.setVisibility(View.GONE);
                llAsiento3.setVisibility(View.GONE);
                llAsiento4.setVisibility(View.GONE);
                llAsiento5.setVisibility(View.GONE);
                llAsiento6.setVisibility(View.GONE);
                llAsiento7.setVisibility(View.GONE);
                llAsiento8.setVisibility(View.GONE);
                llAsiento9.setVisibility(View.GONE);
                btnEligeAsientos.setVisibility(View.GONE);
                ivMapaCine.setVisibility(View.GONE);
                ivTaquilla.setVisibility(View.VISIBLE);
                tvAgradecimiento.setVisibility(View.VISIBLE);
            }
        });

        return vista;
    }
}