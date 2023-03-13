package uem.dam.eurocodefilms.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import uem.dam.eurocodefilms.R;

public class TaquillaFragment extends Fragment {


    private String pelicula;
    private String sinopsis;
    private String sala;

    TextView tvRepreTitulo;
    TextView tvRepreSinopsis;


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

        tvRepreTitulo = vista.findViewById(R.id.tvRepreTitulo);
        tvRepreSinopsis = vista.findViewById(R.id.tvRepreSinopsis);

        tvRepreTitulo.setText(pelicula);
        tvRepreSinopsis.setText(sinopsis);

        return vista;
    }
}