package com.example.tema3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar ac = getSupportActionBar();
        ac.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        ((TextView)findViewById(R.id.textViewNombre)).setText(intent.getStringExtra("nombre"));
        ((RatingBar)findViewById(R.id.ratingBar2)).setRating(intent.getFloatExtra("stars", Float.MAX_VALUE));
        ((Button)findViewById(R.id.buttonReturn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent();
                intent2.putExtra("stars", ((RatingBar)findViewById(R.id.ratingBar2)).getRating());
                setResult(RESULT_OK, intent2);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent2 = new Intent();
        intent2.putExtra("stars", ((RatingBar)findViewById(R.id.ratingBar2)).getRating());
        setResult(RESULT_OK, intent2);
        finish();
    }
}