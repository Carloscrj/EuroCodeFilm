package uem.dam.eurocodefilms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import uem.dam.eurocodefilms.R;

public class ImagenAdapter extends RecyclerView.Adapter<ImagenAdapter.ImagenViewHolder> implements View.OnClickListener{

    private ArrayList<String> datos;
    private View.OnClickListener listener;

    private Context context;

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public ImagenAdapter(ArrayList<String> datos, Context context) {
        this.datos = datos;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
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
        holder.imageView = holder.ivImagen;
        Glide.with(context).load(datos.get(position)).into(holder.ivImagen);
        //Glide.with(context).load(datos.get(position)).into((ImageView) holder.imageView);//holder.bindGraph(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ImagenViewHolder extends RecyclerView.ViewHolder {

        public Object imageView;
        ImageView ivImagen;
        public ImagenViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
        }

        public void bindGraph(Object imagen) {
            this.imageView = imagen;
        }
    }
}
