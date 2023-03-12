package uem.dam.eurocodefilms.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import uem.dam.eurocodefilms.R;
import uem.dam.eurocodefilms.apidata.CineRes;
import uem.dam.eurocodefilms.apidata.Graph;
import uem.dam.eurocodefilms.util.APIRestServicesCines;
import uem.dam.eurocodefilms.util.RetrofitClient;

public class MapaFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapView mMapView;

    Spinner spnMapas;

    Retrofit r = RetrofitClient.getClient(APIRestServicesCines.BASE_URL);
    APIRestServicesCines ars = r.create(APIRestServicesCines.class);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mapa, container, false);

        mMapView = rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        spnMapas = rootView.findViewById(R.id.spnMapas);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        if (networkAvailable()) {
            consultarCines();
        } else {
            Toast.makeText(getContext(), "No hay conexión a internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void consultarCines() {
        Call<CineRes> call = ars.obtenerCines(APIRestServicesCines.CLAVE_KEY);
        call.enqueue(new retrofit2.Callback<CineRes>() {
            @Override
            public void onResponse(Call<CineRes> call, retrofit2.Response<CineRes> response) {
                if (response.isSuccessful()) {
                    CineRes cines = response.body();
                    if (cines != null) {
                        List<Graph> cinesaCines = new ArrayList<>();
                        for (int i = 0; i < cines.getGraph().size(); i++) {
                            if (cines.getGraph().get(i).getTitle().contains("Cinesa")) {
                                cinesaCines.add(cines.getGraph().get(i));
                            }
                        }
                        cines.setGraph(cinesaCines);
                        cargarDatosEnMapa(cines);
                    } else {
                        Log.e("CINES", "No hay datos");
                    }
                } else {
                    Log.e("CINES", "Error en la respuesta del servidor " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<CineRes> call, Throwable t) {
                Log.e("CINES", "Error en la llamada");
            }
        });
    }

    private void cargarDatosEnMapa(CineRes cines) {
        BitmapDescriptor icono;
        LatLng latLng;
        for (int i = 0; i < cines.getGraph().size(); i++) {
            latLng = new LatLng(cines.getGraph().get(i).getLocation().getLatitude(),
                    cines.getGraph().get(i).getLocation().getLongitude());
            icono = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
            mMap.addMarker(new MarkerOptions().position(latLng).title(cines.getGraph().get(i).getTitle()).icon(icono));
        }
    }

    private boolean networkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(40.416775, -3.703790);
        mMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(latLng, 10));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
    }
}