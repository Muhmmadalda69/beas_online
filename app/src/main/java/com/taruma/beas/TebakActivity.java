package com.taruma.beas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class TebakActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tebak);

        // temukan button dengan id
        Button button_l1 = findViewById(R.id.l1);
        Button button_l2 = findViewById(R.id.l2);
        Button button_l3 = findViewById(R.id.l3);
        Button button_l4 = findViewById(R.id.l4);
        Button button_l5 = findViewById(R.id.l5);
        Button button_l6 = findViewById(R.id.l6);
        Button button_l7 = findViewById(R.id.l7);
        Button button_l8 = findViewById(R.id.l8);
        Button button_l9 = findViewById(R.id.l9);
        Button button_l10 = findViewById(R.id.l10);

        //kunci 2
        button_l2.setEnabled(false);
        SharedPreferences mSettings = getSharedPreferences("PREFS",0);
        int nilai = mSettings.getInt("SkorLulus",0);

        //kunci 3
        button_l3.setEnabled(false);
        SharedPreferences mSettings2 = getSharedPreferences("PREFS2",0);
        int nilai2 = mSettings2.getInt("SkorLulus2",0);

        //kunci 4
        button_l4.setEnabled(false);
        SharedPreferences mSettings3 = getSharedPreferences("PREFS3",0);
        int nilai3 = mSettings3.getInt("SkorLulus3",0);

        //kunci 5
        button_l5.setEnabled(false);
        SharedPreferences mSettings4 = getSharedPreferences("PREFS4",0);
        int nilai4 = mSettings4.getInt("SkorLulus4",0);

        button_l6.setEnabled(false);
        SharedPreferences mSettings5 = getSharedPreferences("PREFS5",0);
        int nilai5 = mSettings5.getInt("SkorLulus5",0);

        button_l7.setEnabled(false);
        SharedPreferences mSettings6 = getSharedPreferences("PREFS6",0);
        int nilai6 = mSettings6.getInt("SkorLulus6",0);

        button_l8.setEnabled(false);
        SharedPreferences mSettings7 = getSharedPreferences("PREFS7",0);
        int nilai7 = mSettings7.getInt("SkorLulus7",0);

        button_l9.setEnabled(false);
        SharedPreferences mSettings8 = getSharedPreferences("PREFS8",0);
        int nilai8 = mSettings8.getInt("SkorLulus8",0);

        button_l10.setEnabled(false);
        SharedPreferences mSettings9 = getSharedPreferences("PREFS9",0);
        int nilai9 = mSettings9.getInt("SkorLulus9",0);

        // LEVEL 1
        button_l1.setOnClickListener(view -> {
            Intent intent = new Intent(TebakActivity.this, L1Activity.class);
            startActivity(intent);
            finish();
        });

        //LEVEL 2
        if (nilai >= 70) {
            button_l2.setEnabled(true);
            button_l2.setOnClickListener(view -> {
                Intent intent = new Intent(TebakActivity.this, L2Activity.class);
                startActivity(intent);
                finish();
            });
            //LEVEL 3
            if (nilai2 >= 70) {
                button_l3.setEnabled(true);
                button_l3.setOnClickListener(view -> {
                    Intent intent = new Intent(TebakActivity.this, L3Activity.class);
                    startActivity(intent);
                    finish();
                });
                //LEVEL 4
                if (nilai3 >= 70) {
                    button_l4.setEnabled(true);
                    button_l4.setOnClickListener(view -> {
                        Intent intent = new Intent(TebakActivity.this, L4Activity.class);
                        startActivity(intent);
                        finish();
                    });
                    //LEVEL 5
                    if (nilai4 >= 70) {
                        button_l5.setEnabled(true);
                        button_l5.setOnClickListener(view -> {
                            Intent it = new Intent(TebakActivity.this, L5Activity.class);
                            startActivity(it);
                            finish();
                        });
                        //LEVEL 6
                        if (nilai5 >= 70) {
                            button_l6.setEnabled(true);
                            button_l6.setOnClickListener(view -> {
                                Intent it = new Intent(TebakActivity.this, L6Activity.class);
                                startActivity(it);
                                finish();
                            });
                            if (nilai6 >= 70) {
                                button_l7.setEnabled(true);
                                button_l7.setOnClickListener(view -> {
                                    Intent it = new Intent(TebakActivity.this, L7Activity.class);
                                    startActivity(it);
                                    finish();
                                });
                                if (nilai7 >= 70) {
                                    button_l8.setEnabled(true);
                                    button_l8.setOnClickListener(view -> {
                                        Intent it = new Intent(TebakActivity.this, L8Activity.class);
                                        startActivity(it);
                                        finish();
                                    });
                                    if (nilai8 >= 70) {
                                        button_l9.setEnabled(true);
                                        button_l9.setOnClickListener(view -> {
                                            Intent it = new Intent(TebakActivity.this, L9Activity.class);
                                            startActivity(it);
                                            finish();
                                        });
                                        if (nilai9 >= 70) {
                                            button_l10.setEnabled(true);
                                            button_l10.setOnClickListener(view -> {
                                                Intent it = new Intent(TebakActivity.this, L10Activity.class);
                                                startActivity(it);
                                                finish();
                                            });
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}