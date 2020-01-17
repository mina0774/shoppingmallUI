package com.example.heronation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class RegisterBodyActivity extends AppCompatActivity {
    public int number =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_body);
        final TextView height = (TextView) findViewById(R.id.height_text);
        SeekBar seekBar_height = (SeekBar) findViewById(R.id.seekBar_height);
        seekBar_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                height.setText("" + progress);
        }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                number = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                number = seekBar.getProgress();
            }
        });
    }
}

