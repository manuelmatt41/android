package com.example.tema3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateCheckBox();
        updateSeekBar();
        updateSwitch();
        reset();
        countNumber();
        showRadioToast();
        openNewActivity();
    }

    private void updateCheckBox() {
        ToggleButton tb = findViewById(R.id.toggleButton);
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CheckBox) findViewById(R.id.checkBox)).setChecked(!tb.isChecked());
            }
        });
    }

    private void updateSeekBar() {
        SeekBar sb = findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ((TextView) findViewById(R.id.textViewNumber)).setText(sb.getProgress() + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void updateSwitch() {
        Switch switchBt = findViewById(R.id.switch1);
        switchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchBt.setText(switchBt.isChecked() ? "Activado" : "Desactivado");
            }
        });
    }

    private void reset() {
        ((Button) findViewById(R.id.buttonNormal)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ToggleButton) findViewById(R.id.toggleButton)).setChecked(false);
                ((CheckBox) findViewById(R.id.checkBox)).setChecked(false);
                ((CheckBox) findViewById(R.id.checkBox2)).setChecked(false);
                ((CheckBox) findViewById(R.id.checkBox3)).setChecked(false);
                if (((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId() != -1) {
                    ((RadioButton) findViewById(((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId())).setChecked(false);
                }
                ((Switch) findViewById(R.id.switch1)).setChecked(false);
                ((SeekBar) findViewById(R.id.seekBar)).setProgress(0);
                ((RatingBar) findViewById(R.id.ratingBar)).setRating(0);
                ((EditText) findViewById(R.id.editTextTextPersonName)).setText("");
            }
        });
    }

    private void countNumber() {
        ((Button) findViewById(R.id.buttonWithImage)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = findViewById(R.id.textViewCount);
                try {
                    text.setText((((CheckBox) findViewById(R.id.checkBox2)).isChecked() ? Integer.parseInt(text.getText().toString()) - 1 : Integer.parseInt(text.getText().toString()) + 1) + "");
                } catch (NumberFormatException e) {
                    text.setText("1");
                }
            }
        });
    }

    private void showRadioToast() {
        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), ((RadioButton) view).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        };
        ((RadioButton)findViewById(R.id.radioButton)).setOnClickListener(click);
        ((RadioButton)findViewById(R.id.radioButton2)).setOnClickListener(click);
    }

    private void openNewActivity() {
        ((ImageButton)findViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("nombre", ((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString());
                intent.putExtra("stars", ((RatingBar)findViewById(R.id.ratingBar)).getRating());
                launcher.launch(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Intent intentReturn = result.getData();
                float stars = intentReturn.getFloatExtra("stars", Float.MAX_VALUE);
                ((TextView)findViewById(R.id.textViewCount)).setText( stars + "");
                ((RatingBar)findViewById(R.id.ratingBar)).setRating(stars);
                Log.i("Stars", stars + "");
            }
        }
    });
}