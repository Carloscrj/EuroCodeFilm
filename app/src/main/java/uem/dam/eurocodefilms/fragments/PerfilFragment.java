package uem.dam.eurocodefilms.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uem.dam.eurocodefilms.LoginActivity;
import uem.dam.eurocodefilms.R;
import uem.dam.eurocodefilms.data.Perfil;

public class PerfilFragment extends Fragment implements View.OnClickListener {

    TextView tvPerfil;
    Button btnBorrarCuenta;
    EditText etNuevaPwd;
    Button btnCambiarPwd;
    private FirebaseAuth fba;
    private FirebaseUser user;

    private DatabaseReference dbRef;

    public PerfilFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dbRef = FirebaseDatabase.getInstance().getReference("PERFILES");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_perfil, container, false);
        tvPerfil = vista.findViewById(R.id.tvPerfil);
        btnBorrarCuenta = vista.findViewById(R.id.btnBorrarCuenta);
        btnBorrarCuenta.setOnClickListener(this);
        etNuevaPwd = vista.findViewById(R.id.etNuevaPwd);
        btnCambiarPwd = vista.findViewById(R.id.btnCambiarPwd);
        btnCambiarPwd.setOnClickListener(this);
        user = FirebaseAuth.getInstance().getCurrentUser();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                for (com.google.firebase.database.DataSnapshot data : dataSnapshot.getChildren()) {
                    Perfil perfil = data.getValue(Perfil.class);
                    if (user != null) {
                        if (user.getEmail().equals(perfil.getEmail())) {
                            String nombre = perfil.getNombre();
                            String nombreCap = nombre.substring(0, 1).toUpperCase() + nombre.substring(1);
                            tvPerfil.setText(nombreCap);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ERROR", "Error al leer de la base de datos");
            }
        });
        return vista;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnBorrarCuenta) borrarCuenta();
        if (v.getId() == R.id.btnCambiarPwd) cambiarPwd();
    }

    private void cambiarPwd() {
        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();
        String nuevaPwd = etNuevaPwd.getText().toString();
        boolean validPwd = isValidPwd(nuevaPwd);

        if (validPwd) {
            user.updatePassword(nuevaPwd).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getActivity(), R.string.cambiar_pwd_ok, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), R.string.cambiar_pwd_no_ok, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getActivity(), R.string.pwd_no_valido, Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isValidPwd(String nuevaPwd) {
        Pattern pattern = Pattern.compile(LoginActivity.REGEX_PWD);
        Matcher matcher = pattern.matcher(nuevaPwd);
        return matcher.matches();
    }

    private void borrarCuenta() {
        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();
        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
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