package com.example.peliculas;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PortadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);
        Intent intent = getIntent();
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(((Pelicula)intent.getSerializableExtra("movie")).getPortada());
        ((TextView)findViewById(R.id.textView)).setText(((Pelicula)intent.getSerializableExtra("movie")).getSinopsis());
        setTitle(((Pelicula)intent.getSerializableExtra("movie")).getTitulo());

        ((ImageView)findViewById(R.id.imageView5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                watchYoutubeVideo(((Pelicula)intent.getSerializableExtra("movie")).idYoutube);
            }
        });
    }
    public void watchYoutubeVideo(String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }
}