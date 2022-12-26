package com.example.beas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Closeable;

public class MainActivity extends AppCompatActivity{
    private TextView tv_nama;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.bt_th);
        Button bt_ts = findViewById(R.id.bt_ts);
        Button bt_pustaka = findViewById(R.id.bt_pustaka);
        Button bt_profile = findViewById(R.id.bt_profil);


        button.setOnClickListener(view12 -> {
            Intent intent = new Intent(MainActivity.this, TebakActivity.class);
            startActivity(intent);
        });

        bt_ts.setOnClickListener(view1 -> {
            Intent it = new Intent(MainActivity.this, LihatSkorActivity.class);
            startActivity(it);
        });

        bt_pustaka.setOnClickListener(view1 -> {
            Intent i = new Intent(MainActivity.this, PustakaActivity.class);
            startActivity(i);
        });
        bt_profile.setOnClickListener(view1 -> {
            Intent i = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(i);
        });
    }
}