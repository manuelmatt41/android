package com.example.peliculas;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListadoAdapter extends RecyclerView.Adapter<ListadoAdapter.ViewHolderListado> {
    ArrayList<Pelicula> movies;
    public int selectedPos;
    View.OnClickListener listener;

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setSelectedPos(int selectedPos) {
        if (selectedPos == RecyclerView.NO_POSITION) {
            this.selectedPos = RecyclerView.NO_POSITION;
            notifyItemChanged(selectedPos);
        } else {
            if (this.selectedPos > RecyclerView.NO_POSITION) {
                notifyItemChanged(this.selectedPos);
            }
            this.selectedPos = selectedPos;
            notifyItemChanged(selectedPos);
        }
    }

    public int getSelectedPos() {
        return selectedPos;
    }

    public ListadoAdapter(ArrayList<Pelicula> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolderListado onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderListado(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_listado_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListado holder, int position) {
        holder.imageViewPortada.setImageResource(movies.get(position).portada);
        holder.imageViewEdades.setImageResource(movies.get(position).clasi);
        holder.imageViewFavourites.setImageResource(movies.get(position).favorita ? R.drawable.ic_ac_heart : R.drawable.ic_ac_empty_heart);
        holder.textViewDirector.setText(movies.get(position).director);
        holder.textViewDuracion.setText(movies.get(position).getDuracion() + "");
        holder.textViewFecha.setText(movies.get(position).fecha.toString());
        holder.textViewSala.setText(movies.get(position).sala);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolderListado extends RecyclerView.ViewHolder {
        ImageView imageViewFavourites;
        ImageView imageViewEdades;
        ImageView imageViewPortada;
        TextView textViewDirector;
        TextView textViewFecha;
        TextView textViewDuracion;
        TextView textViewSala;

        public ViewHolderListado(@NonNull View itemView) {
            super(itemView);
            imageViewFavourites = itemView.findViewById(R.id.imageViewFavorite);
            imageViewEdades = itemView.findViewById(R.id.imageView4);
            imageViewPortada = itemView.findViewById(R.id.imageView3);
            textViewDirector = itemView.findViewById(R.id.textViewDirector);
            textViewFecha = itemView.findViewById(R.id.textViewFecha);
            textViewDuracion = itemView.findViewById(R.id.textViewDuracion);
            textViewSala = itemView.findViewById(R.id.textViewSala);
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
