package uem.dam.eurocodefilms.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import uem.dam.eurocodefilms.R;
import uem.dam.eurocodefilms.adapter.ImagenAdapter;

public class CarteleraFragment extends Fragment implements View.OnClickListener{

    public static final String CLAVE_PELICULA = "PELICULA";
    public static final String CLAVE_SINOPSIS = "SINOPSIS";
    Spinner spnCartelera;
    Button btnCartelera;

    TextView tvCartCines;
    RecyclerView rvCartelera;
    ImagenAdapter imagenAdapter;

    FragmentManager fm;
    FragmentTransaction ft;

    private DatabaseReference imagenesRef;
    private DatabaseReference cineRef;

    String cineSeleccionado = "";

    private ArrayList<String> imageList;

    public CarteleraFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_cartelera, container, false);

        spnCartelera = vista.findViewById(R.id.spnCartelera);
        btnCartelera = vista.findViewById(R.id.btnCartelera);
        tvCartCines = vista.findViewById(R.id.tvCartCines);
        rvCartelera = vista.findViewById(R.id.rvCartelera);
        btnCartelera.setOnClickListener(this);
        rvCartelera.setLayoutManager(new LinearLayoutManager(getActivity()));
        imageList = new ArrayList<>();
        imagenAdapter = new ImagenAdapter(imageList, getActivity());
        imagenAdapter.setOnClickListener(this);
        rvCartelera.setAdapter(imagenAdapter);
        return vista;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCartelera) {
            tvCartCines.setText(spnCartelera.getSelectedItem().toString());

            switch (spnCartelera.getSelectedItemPosition()) {
                case 0:
                    cineSeleccionado = "CINE_CALLE_FUENCARRAL";
                    break;
                case 1:
                    cineSeleccionado = "CINE_LAS_ROSAS";
                    break;
                case 2:
                    cineSeleccionado = "CINE_LA_GAVIA";
                    break;
                case 3:
                    cineSeleccionado = "CINE_MANOTERAS";
                    break;
                case 4:
                    cineSeleccionado = "CINE_MENDEZ_ALVARO";
                    break;
                case 5:
                    cineSeleccionado = "CINE_PRINCIPE_PIO";
                    break;
            }

            imagenesRef = FirebaseDatabase.getInstance().getReference(cineSeleccionado).child("imagenes");



            imagenesRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String imageUrl = dataSnapshot.getValue(String.class);
                        imageList.add(imageUrl);
                    }
                    imagenAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        } else {
            Log.d("TAG", "onClick: " + v.getId());
            if (tvCartCines.getText().toString().equals("CALLE FUENCARRAL")){
                cineSeleccionado = "CINE_CALLE_FUENCARRAL";
            } else if (tvCartCines.getText().toString().equals("LAS ROSAS")){
                cineSeleccionado = "CINE_LAS_ROSAS";
            } else if (tvCartCines.getText().toString().equals("LA GAVIA")){
                cineSeleccionado = "CINE_LA_GAVIA";
            } else if (tvCartCines.getText().toString().equals("MANOTERAS")){
                cineSeleccionado = "CINE_MANOTERAS";
            } else if (tvCartCines.getText().toString().equals("MENDEZ ALVARO")){
                cineSeleccionado = "CINE_MENDEZ_ALVARO";
            } else if (tvCartCines.getText().toString().equals("PRINCIPE PIO")){
                cineSeleccionado = "CINE_PRINCIPE_PIO";
            }

            cineRef = FirebaseDatabase.getInstance().getReference(cineSeleccionado);
            String pelicula = "";
            String sinopsis = "";


            int pos = rvCartelera.getChildAdapterPosition(v);
            if (pos == 0){
                pelicula = String.valueOf(cineRef.child("peliculas").child("pelicula1"));
                sinopsis = String.valueOf(cineRef.child("sinopsis").child("sinopsis1"));
            }else if (pos == 1){
                pelicula = String.valueOf(cineRef.child("peliculas").child("pelicula2"));
                sinopsis = String.valueOf(cineRef.child("sinopsis").child("sinopsis2"));
            }else if (pos == 2){
                pelicula = String.valueOf(cineRef.child("peliculas").child("pelicula3"));
                sinopsis = String.valueOf(cineRef.child("sinopsis").child("sinopsis3"));
            }else if (pos == 3){
                pelicula = String.valueOf(cineRef.child("peliculas").child("pelicula4"));
                sinopsis = String.valueOf(cineRef.child("sinopsis").child("sinopsis4"));
            }

            fm = getActivity().getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.flContenedor, TaquillaFragment.newInstance(pelicula, sinopsis));
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}