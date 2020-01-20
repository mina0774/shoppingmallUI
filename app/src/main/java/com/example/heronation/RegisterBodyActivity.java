package com.example.heronation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class RegisterBodyActivity extends AppCompatActivity {
    public int number_height=0;
    public int number_weight=0;
    public int number_age =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_body);
        final TextView height = (TextView) findViewById(R.id.height_text);
        SeekBar seekBar_height = (SeekBar) findViewById(R.id.seekBar_height);
        seekBar_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar_height, int progress, boolean fromUser) {
                height.setText("" + progress);
        }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar_height) {
                number_height = seekBar_height.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar_height) {
                number_height = seekBar_height.getProgress();
            }
        });
        final TextView weight = (TextView) findViewById(R.id.weight_text);
        SeekBar seekBar_weight = (SeekBar) findViewById(R.id.seekBar_weight);
        seekBar_weight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar_weight, int progress, boolean fromUser) {
                weight.setText("" + progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar_weight) {
                number_weight = seekBar_weight.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar_weight) {
                number_weight = seekBar_weight.getProgress();
            }
        });
        final TextView age = (TextView) findViewById(R.id.age_text);
        SeekBar seekBar_age = (SeekBar) findViewById(R.id.seekBar_age);
        seekBar_age.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar_age, int progress, boolean fromUser) {
                age.setText("" + progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar_age) {
                number_age = seekBar_age.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar_age) {
                number_age = seekBar_age.getProgress();
            }
        });
    }
    public void click_registerbody_previous(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}

