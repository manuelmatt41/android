package com.example.peliculas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

public class AnyadirActivity extends AppCompatActivity {
    Pelicula pelicula;
    Date date;
    TextView director;
    TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anyadir);
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MONTH, -1);
        ((RadioButton)findViewById(R.id.radioButton)).setTag(R.drawable.g);
        ((RadioButton)findViewById(R.id.radioButton2)).setTag(R.drawable.pg);
        ((RadioButton)findViewById(R.id.radioButton3)).setTag(R.drawable.pg13);
        ((RadioButton)findViewById(R.id.radioButton4)).setTag(R.drawable.r);
        ((RadioButton)findViewById(R.id.radioButton5)).setTag(R.drawable.nc17);
        ((CalendarView) findViewById(R.id.calendarView)).setDate(now.getTimeInMillis());
        ((CalendarView) findViewById(R.id.calendarView)).setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                now.set(i, i1, i2);
                date = new Date(now.getTimeInMillis());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AnyadirActivity.this, MainActivity.class);
        titulo = findViewById(R.id.editTextTitulo);
        director = findViewById(R.id.editTextDirector);
        intent.putExtra("movie", new Pelicula(titulo.getText().toString(), director.getText().toString(), 0, date, "",(int) ((RadioButton)findViewById(((RadioGroup)findViewById(R.id.radioGroup)).getCheckedRadioButtonId())).getTag(), R.drawable.sincara));
        Toast.makeText(AnyadirActivity.this, "pelicula.getTitulo()", Toast.LENGTH_LONG).show();
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }
}