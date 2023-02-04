package com.example.beas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class DaftarActivity extends AppCompatActivity {

    private Button bt_daftar, bt_login;
    private EditText et_username, et_email, et_password, et_konfirpassword;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private FirebaseDatabase database;
    private DatabaseReference mRef;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_konfirpassword = findViewById(R.id.et_konfirpassword);
        bt_login = findViewById(R.id.bt_login);
        bt_daftar = findViewById(R.id.bt_daftar);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference().child("db_skor");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(DaftarActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan tunggu...");
        progressDialog.setCancelable(false);

        bt_login.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });

        bt_daftar.setOnClickListener(view -> {
            progressDialog.show();
            if(et_username.getText().length()>0 &&
                    et_email.getText().length()>0 &&
                    et_password.getText().length()>0){
                checkDisplayName(et_username.getText().toString());
            }else {
                Toast.makeText(getApplicationContext(),"Silahkan Masukan dulu semua data!",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }

    private void daftar(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful() && task.getResult()!=null) {
                        FirebaseUser firebaseUser = task.getResult().getUser();
                        if (firebaseUser != null){
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(et_username.getText().toString())
                                    .build();
                            progressDialog.dismiss();
                            firebaseUser.updateProfile(profileUpdates).addOnCompleteListener(this::onComplete);
                        }else {
                            Toast.makeText(DaftarActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // If sign in fails, display a message to the user.;
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkDisplayName(String displayName) {
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild(displayName)) {
                        progressDialog.dismiss();
                        Toast.makeText(DaftarActivity.this, "Nama sudah digunakan", Toast.LENGTH_SHORT).show();
                    } else {
                        if (et_password.getText().toString().equals(et_konfirpassword.getText().toString())) {
                            daftar(et_email.getText().toString(),
                                    et_password.getText().toString());
                        } else {
                            Toast.makeText(getApplicationContext(), "Password tidak sama!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DaftarActivity.this, "gagal menambahkan nama", Toast.LENGTH_SHORT).show();
            }
        });
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

    private void onComplete(Task<Void> task1) {
        reload();
    }
}