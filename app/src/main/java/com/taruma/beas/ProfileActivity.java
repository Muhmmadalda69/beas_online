package com.example.beas;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {
    private Button bt_hapusakun, bt_logoout, bt_editprofile, bt_editpassword;
    private TextView tv_nama;
    private ImageView foto_akun;
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbSkorRef = database.getReference("db_skor");
    DatabaseReference dbTotal = database.getReference("db_total");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_nama = findViewById(R.id.tv_nama);
        foto_akun = findViewById(R.id.foto_akun);

        bt_logoout = findViewById(R.id.bt_logout);
        bt_hapusakun = findViewById(R.id.bt_hapusakun);
        bt_editprofile = findViewById(R.id.bt_editprofile);
        bt_editpassword = findViewById(R.id.bt_editpassword);

        mAuth = FirebaseAuth.getInstance();

        bt_hapusakun.setOnClickListener(view -> {
            //dbTotal.child(Objects.requireNonNull(firebaseUser.getDisplayName())).removeValue();
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ProfileActivity.this);
            // set title dialog
            alertDialogBuilder.setTitle("Yakin menghapus akun anda?");

            // set pesan dari dialog
            alertDialogBuilder
                    .setMessage("Ketika akun dihapus, semua data akan ikut terhapus")
                    .setIcon(R.mipmap.ic_logo)
                    .setCancelable(false)
                    .setPositiveButton("Ya", (dialog, id) -> {
                        FirebaseUser user = mAuth.getCurrentUser();
                        assert user != null;
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // jika tombol diklik, maka akan menghapus akun
                                    dbSkorRef.child(Objects.requireNonNull(firebaseUser.getDisplayName())).removeValue();
                                    Toast.makeText(ProfileActivity.this, "Akun sudah terhapus", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(ProfileActivity.this, "Akun gagal dihapus, coba ulang!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    })
                    .setNegativeButton("Tidak", (dialog, id) -> {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    });

            // membuat alert dialog dari builder
            AlertDialog alertDialog = alertDialogBuilder.create();

            // menampilkan alert dialog
            alertDialog.show();
        });

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