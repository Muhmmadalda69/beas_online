package com.example.beas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText et_username, et_password;
    private TextView lupa_password;
    private Button bt_login, bt_daftar;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        lupa_password = findViewById(R.id.tv_lupapassword);
        bt_login = findViewById(R.id.bt_login);
        bt_daftar = findViewById(R.id.bt_daftar);

        String email =et_username.getText().toString();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan tunggu...");
        progressDialog.setCancelable(false);

        bt_daftar.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, DaftarActivity.class);
            startActivity(intent);
            finish();
        });

        bt_login.setOnClickListener(view -> {
            if (et_username.getText().length()>0 && et_password.getText().length()>0){
                login(et_username.getText().toString(), et_password.getText().toString());
            }else{
                Toast.makeText(getApplicationContext(),"Silahkan Masukan dulu semua data!",Toast.LENGTH_SHORT).show();
            }
        });

        lupa_password.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, LupaPasswordActivity.class));
            finish();
        });
    }

    private void login(String email,String password){
        progressDialog.show();
        // Melakukan validasi email menggunakan Patterns.EMAIL_ADDRESS.matcher
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // Email valid, lanjutkan dengan tindakan yang diinginkan
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if(task.isSuccessful() && task.getResult()!=null){
                    progressDialog.cancel();
                    if(task.getResult().getUser()!=null){
                        reload();
                    }else {
                        Toast.makeText(LoginActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    progressDialog.cancel();
                    Toast.makeText(LoginActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Email tidak valid, berikan umpan balik ke pengguna
            Toast.makeText(this, "Penulisan Email salah!", Toast.LENGTH_LONG).show();
            progressDialog.cancel();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    private void reload() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}