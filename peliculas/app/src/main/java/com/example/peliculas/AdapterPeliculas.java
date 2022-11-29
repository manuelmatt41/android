package com.example.peliculas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterPeliculas extends RecyclerView.Adapter<AdapterPeliculas.VHPeliculas> {
    ArrayList<Pelicula> movies;
    View.OnClickListener listener;

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    int selectedPos = RecyclerView.NO_POSITION;

    public int getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(int nuevaPos) {
        if (nuevaPos == this.selectedPos) {
            this.selectedPos = RecyclerView.NO_POSITION;
            notifyItemChanged(nuevaPos);
        } else {
            if (this.selectedPos > RecyclerView.NO_POSITION) {
                notifyItemChanged(this.selectedPos);
            }
            this.selectedPos = nuevaPos;
            notifyItemChanged(nuevaPos);
        }
    }

    public AdapterPeliculas(ArrayList<Pelicula> movies) {
        this.movies = movies;
        this.selectedPos = RecyclerView.NO_POSITION;
    }

    @NonNull
    @Override
    public VHPeliculas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VHPeliculas(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_peliculas_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VHPeliculas holder, int position) {
        holder.ivPortada.setImageResource(movies.get(position).portada);
        holder.ivEdades.setImageResource(movies.get(position).clasi);
        holder.tvTitulo.setText(movies.get(position).titulo);
        holder.tvDirector.setText(movies.get(position).director);
        if (getSelectedPos() == position) {
            holder.itemView.setBackgroundResource(R.color.rv_peliculas_backcolor_selected);
        } else {
            holder.itemView.setBackgroundResource(R.color.rv_peliculas_backcolor);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class VHPeliculas extends RecyclerView.ViewHolder {
        ImageView ivPortada;
        ImageView ivEdades;
        TextView tvTitulo;
        TextView tvDirector;

        public VHPeliculas(@NonNull View itemView) {
            super(itemView);
            ivPortada = itemView.findViewById(R.id.imageView);
            ivEdades = itemView.findViewById(R.id.imageView2);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDirector = itemView.findViewById(R.id.tvDirector);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setSelectedPos(getAdapterPosition());
                    listener.onClick(view);
                }
            });
        }
    }
}
