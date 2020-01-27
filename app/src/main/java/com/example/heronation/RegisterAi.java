package com.example.heronation;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class RegisterAi extends AppCompatActivity {
    boolean casual_state = false;
    boolean street_state = false;
    boolean vintage_state = false;
    boolean sexy_state = false;
    boolean modern_state = false;
    boolean feminine_state = false;
    boolean dandy_state = false;
    boolean lovely_state = false;
    boolean unique_state = false;
    boolean classic_state = false;
    boolean sporty_state = false;
    boolean layered_state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_ai);

        final Button casual_btn = (Button) findViewById(R.id.btn_casual);
        final Button street_btn = (Button) findViewById(R.id.btn_street);
        final Button vintage_btn = (Button) findViewById(R.id.btn_vintage);
        final Button sexy_btn = (Button) findViewById(R.id.btn_sexy);
        final Button modern_btn = (Button) findViewById(R.id.btn_modern);
        final Button feminine_btn = (Button) findViewById(R.id.btn_feminine);
        final Button dandy_btn = (Button) findViewById(R.id.btn_dandy);
        final Button lovely_btn = (Button) findViewById(R.id.btn_lovely);
        final Button unique_btn = (Button) findViewById(R.id.btn_unique);
        final Button classic_btn = (Button) findViewById(R.id.btn_classic);
        final Button sporty_btn = (Button) findViewById(R.id.btn_sporty);
        final Button layered_btn = (Button) findViewById(R.id.btn_layered);

        casual_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (casual_state == false) {
                    casual_state = true;
                    casual_btn.setBackgroundResource(R.drawable.btn_background_black);
                } else {
                    casual_btn.setBackgroundResource(R.drawable.btn_background);
                    casual_state = false;
                }
            }
        });
        street_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (street_state== false) {
                    street_state = true;
                    street_btn.setBackgroundResource(R.drawable.btn_background_black);
                } else {
                    street_btn.setBackgroundResource(R.drawable.btn_background);
                    street_state = false;
                }
            }
        });
        vintage_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vintage_state == false) {
                     vintage_state = true;
                    vintage_btn.setBackgroundResource(R.drawable.btn_background_black);
                } else {
                    vintage_btn.setBackgroundResource(R.drawable.btn_background);
                    vintage_state = false;
                }
            }
        });

        sexy_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sexy_state == false) {
                    sexy_state = true;
                    sexy_btn.setBackgroundResource(R.drawable.btn_background_black);
                } else {
                    sexy_btn.setBackgroundResource(R.drawable.btn_background);
                    sexy_state = false;
                }
            }
        });
    }

    public void click_registerai_previous(View view) {
        Intent intent = new Intent(this, RegisterBodyActivity.class);
        startActivity(intent);
    }

    public void click_registerai_next(View view) {
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
    }
}
