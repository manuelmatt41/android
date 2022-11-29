package com.example.tema3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        boolean[] checkedBoxes = intent.getBooleanArrayExtra("checkedBoxes");
        for (int i = 0; i < checkedBoxes.length; i++) {
            Log.i("Checkes " + i,"" + checkedBoxes[i]);
        }
    }
}