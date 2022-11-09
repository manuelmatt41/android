package com.example.tema3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

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
}