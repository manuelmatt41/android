package com.example.peliculas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FavoritosActivity extends AppCompatActivity {
    ArrayList<String> titulos;
    ArrayAdapter<String> adapter;
    ArrayList<Pelicula> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facoritos);
        Intent intent = getIntent();
        titulos = new ArrayList<>();
        movies = (ArrayList<Pelicula>) intent.getSerializableExtra("movies");
        for (int i = 0; i < movies.size(); i++) {
            titulos.add(movies.get(i).getTitulo());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, titulos);
        ListView lv = findViewById(R.id.lv);
        lv.setAdapter(adapter);
        lv.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        for (int i = 0; i < movies.size(); i++) {
            lv.setItemChecked(i, movies.get(i).getFavorita());
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                movies.get(i).setFavorita(lv.isItemChecked(i));
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(FavoritosActivity.this, MainActivity.class);
        back.putExtra("movies", movies);
        setResult(RESULT_OK, back);
        finish();
        super.onBackPressed();
    }
}