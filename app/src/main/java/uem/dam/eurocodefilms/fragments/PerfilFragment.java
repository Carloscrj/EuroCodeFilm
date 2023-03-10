package uem.dam.eurocodefilms.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uem.dam.eurocodefilms.LoginActivity;
import uem.dam.eurocodefilms.R;

public class PerfilFragment extends Fragment implements View.OnClickListener {
    Button btnBorrarCuenta;
    EditText etNuevaPwd;
    Button btnCambiarPwd;
    private FirebaseAuth fba;
    private FirebaseUser user;

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
        etNuevaPwd = vista.findViewById(R.id.etNuevaPwd);
        btnCambiarPwd = vista.findViewById(R.id.btnCambiarPwd);
        btnCambiarPwd.setOnClickListener(this);
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