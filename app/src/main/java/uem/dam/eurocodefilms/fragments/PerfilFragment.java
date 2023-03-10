package uem.dam.eurocodefilms.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import uem.dam.eurocodefilms.LoginActivity;
import uem.dam.eurocodefilms.R;

public class PerfilFragment extends Fragment implements View.OnClickListener {
    Button btnBorrarCuenta;
    private FirebaseAuth fba;

    public PerfilFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_perfil, container, false);
        btnBorrarCuenta = vista.findViewById(R.id.btnBorrarCuenta);
        btnBorrarCuenta.setOnClickListener(this);
        return vista;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnBorrarCuenta) borrarCuenta();
    }

    private void borrarCuenta() {
        fba = FirebaseAuth.getInstance();
        fba.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), R.string.borrar_ok, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), R.string.borrar_no_ok, Toast.LENGTH_SHORT).show();
                }
            }
        });
        fba.signOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}