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
    public static final String CLAVE_SALA = "SALA";
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

            imageList.clear();

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
            if (tvCartCines.getText().toString().equals("CALLE FUENCARRAL")) {
                cineSeleccionado = "CINE_CALLE_FUENCARRAL";
            } else if (tvCartCines.getText().toString().equals("LAS ROSAS")) {
                cineSeleccionado = "CINE_LAS_ROSAS";
            } else if (tvCartCines.getText().toString().equals("LA GAVIA")) {
                cineSeleccionado = "CINE_LA_GAVIA";
            } else if (tvCartCines.getText().toString().equals("MANOTERAS")) {
                cineSeleccionado = "CINE_MANOTERAS";
            } else if (tvCartCines.getText().toString().equals("MENDEZ ALVARO")) {
                cineSeleccionado = "CINE_MENDEZ_ALVARO";
            } else if (tvCartCines.getText().toString().equals("PRINCIPE PIO")) {
                cineSeleccionado = "CINE_PRINCIPE_PIO";
            }

            cineRef = FirebaseDatabase.getInstance().getReference(cineSeleccionado);
            int pos = rvCartelera.getChildAdapterPosition(v);

            String[] peliculas = {"pelicula1", "pelicula2", "pelicula3", "pelicula4"};
            String[] sinopsisList = {"sinopsis1", "sinopsis2", "sinopsis3", "sinopsis4"};
            String[] salaList = {"sala1", "sala2", "sala3", "sala4"};

            cineRef.child("peliculas").child(peliculas[pos]).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String pelicula = dataSnapshot.getValue(String.class);
                    cineRef.child("sinopsis").child(sinopsisList[pos]).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String sinopsis = dataSnapshot.getValue(String.class);
                            cineRef.child("salas").child(salaList[pos]).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    enviarDatosPelicula(dataSnapshot, pelicula, sinopsis);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Log.d("TAG", "Failed to read value.", databaseError.toException());
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.d("TAG", "Failed to read value.", databaseError.toException());
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d("TAG", "Failed to read value.", databaseError.toException());
                }
            });
        }
    }

    private void enviarDatosPelicula(@NonNull DataSnapshot dataSnapshot, String pelicula, String sinopsis) {
        String sala = dataSnapshot.getValue(String.class);
        fm = getActivity().getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.flContenedor, TaquillaFragment.newInstance(pelicula, sinopsis, sala));
        ft.addToBackStack(null);
        ft.commit();
    }

}