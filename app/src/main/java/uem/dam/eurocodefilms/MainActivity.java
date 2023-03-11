package uem.dam.eurocodefilms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import uem.dam.eurocodefilms.fragments.CarteleraFragment;
import uem.dam.eurocodefilms.fragments.InicioFragment;
import uem.dam.eurocodefilms.fragments.MapaFragment;
import uem.dam.eurocodefilms.fragments.PerfilFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnCartelera;
    ImageButton btnMapa;
    ImageButton btnPerfil;
    FragmentManager fm;
    FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        ft.add(R.id.flContenedor, new InicioFragment());
        ft.commit();

        btnCartelera = findViewById(R.id.btnCartelera);
        btnMapa = findViewById(R.id.btnMapa);
        btnPerfil = findViewById(R.id.btnPerfil);

        btnCartelera.setOnClickListener(this);
        btnMapa.setOnClickListener(this);
        btnPerfil.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCartelera) cargarCartelera();
        else if (v.getId() == R.id.btnMapa) cargarMapa();
        else if (v.getId() == R.id.btnPerfil) cargarPerfil();
    }

    private void cargarCartelera() {
        ft = fm.beginTransaction();
        ft.replace(R.id.flContenedor, new CarteleraFragment());
        ft.addToBackStack(null);
        ft.commit();

    }

    private void cargarMapa() {
        ft = fm.beginTransaction();
        ft.replace(R.id.flContenedor, new MapaFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    private void cargarPerfil() {
        ft = fm.beginTransaction();
        ft.replace(R.id.flContenedor, new PerfilFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

}