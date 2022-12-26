package com.example.beas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    private Button bt_logoout, bt_editprofile, bt_editpassword;
    private TextView tv_nama;
    private ImageView foto_akun;
    private FirebaseUser firebaseUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_nama = findViewById(R.id.tv_nama);
        foto_akun = findViewById(R.id.foto_akun);

        bt_logoout = findViewById(R.id.bt_logout);
        bt_editprofile = findViewById(R.id.bt_editprofile);
        bt_editpassword = findViewById(R.id.bt_editpassword);


        bt_logoout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
        });

        bt_editprofile.setOnClickListener(view -> {
            startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
            finish();
        });

        bt_editpassword.setOnClickListener(view -> {
            startActivity(new Intent(ProfileActivity.this, EditPasswordActivity.class));
            finish();
        });

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {
            if(firebaseUser.getDisplayName()!=null){
                tv_nama.setText(firebaseUser.getDisplayName());
                foto_akun.setImageURI(firebaseUser.getPhotoUrl());

            }else{
                Toast.makeText(ProfileActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
            }

        }
    }
}