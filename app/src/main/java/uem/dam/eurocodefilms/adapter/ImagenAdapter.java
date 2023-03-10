package uem.dam.eurocodefilms.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import java.util.ArrayList;

import uem.dam.eurocodefilms.R;
import uem.dam.eurocodefilms.data.Cine;

public class ImagenAdapter extends RecyclerView.Adapter<ImagenAdapter.ImagenViewHolder> implements View.OnClickListener{

    private ArrayList<Cine> datos;
    private View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public ImagenAdapter(ArrayList<Cine> datos, RequestManager with) {
        this.datos = datos;
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public ImagenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagen_item, parent, false);
        //el propio adaptador es el escuchador de cada una de sus vistas
        v.setOnClickListener(this);
        return new ImagenViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagenAdapter.ImagenViewHolder holder, int position) {
        holder.bindGraph(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ImagenViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImagen;
        public ImagenViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
        }

        public void bindGraph(Cine cine) {

        }
    }
}
