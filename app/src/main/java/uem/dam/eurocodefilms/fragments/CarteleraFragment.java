package uem.dam.eurocodefilms.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import uem.dam.eurocodefilms.R;
import uem.dam.eurocodefilms.adapter.ImagenAdapter;

public class CarteleraFragment extends Fragment implements View.OnClickListener{

    Spinner spnCartelera;
    Button btnCartelera;
    RecyclerView rvCartelera;
    RecyclerView.LayoutManager llm;

    ImagenAdapter imagenAdapter;

    private DatabaseReference imagenesRef;

    private ArrayList<String> imageList;

    public CarteleraFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_cartelera, container, false);

        spnCartelera = vista.findViewById(R.id.spnCartelera);
        btnCartelera = vista.findViewById(R.id.btnCartelera);
        rvCartelera = vista.findViewById(R.id.rvCartelera);

        btnCartelera.setOnClickListener(this);

        return vista;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCartelera) {
            String cineSeleccionado = "";

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

            rvCartelera.setLayoutManager(new LinearLayoutManager(getActivity()));
            imageList = new ArrayList<>();
            imagenAdapter = new ImagenAdapter(imageList, getActivity());
            rvCartelera.setAdapter(imagenAdapter);



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
        }
    }

    /*private void cargarRV(List<Cine> images) {
        imagenAdapter = new ImagenAdapter((ArrayList<Cine>) images, Glide.with(this));
        llm = new LinearLayoutManager(getActivity());
        rvCartelera.setHasFixedSize(true);
        rvCartelera.setLayoutManager(llm);
        rvCartelera.setAdapter(imagenAdapter);
    }*/
}