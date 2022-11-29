package com.example.peliculas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ListadoCompletoActivity extends AppCompatActivity {
    ArrayList<Pelicula> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_completo);
        Intent intent = getIntent();
        movies = (ArrayList<Pelicula>) intent.getSerializableExtra("movies");
        RecyclerView rc = findViewById(R.id.rvListado);
        GridLayoutManager gridLayout = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        ListadoAdapter adapterPeliculas = new ListadoAdapter(movies);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListadoCompletoActivity.this, PortadaActivity.class);
                intent.putExtra("movie", movies.get(adapterPeliculas.getSelectedPos()));
                startActivity(intent);
            }
        };
        adapterPeliculas.setListener(listener);
        rc.setLayoutManager(gridLayout);
        rc.setAdapter(adapterPeliculas);
    }
}