package com.example.beas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EditPasswordActivity extends AppCompatActivity {

    private EditText et_pwlama, et_pwbaru, et_konfirpw, et_email;
    private Button bt_save, bt_batal;
    private ProgressDialog progressDialog;
    private String getPassword;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        et_pwlama = findViewById(R.id.et_pwlama);
        et_pwbaru = findViewById(R.id.et_pwbaru);
        et_konfirpw = findViewById(R.id.et_konfirpw);
        et_email = findViewById(R.id.et_email);
        bt_save = findViewById(R.id.bt_save);
        bt_batal = findViewById(R.id.bt_batal);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        progressDialog = new ProgressDialog(EditPasswordActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan tunggu...");
        progressDialog.setCancelable(false);

        bt_save.setOnClickListener(view -> {
            progressDialog.show();
            if(et_pwlama.getText().toString().equals(firebaseUser.getEmail())){
                if (et_pwbaru.getText().toString().equals(et_konfirpw.getText().toString())) {

                    getPassword = et_pwbaru.getText().toString().trim();
                    //Melakukan Proses Update, dengan memasukan password beru
                    firebaseUser.updatePassword(getPassword)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    //Mengecek status keberhasilan saat proses update Password
                                    if (task.isSuccessful()) {
                                        Toast.makeText(EditPasswordActivity.this, "Password Berhasil Diubah", Toast.LENGTH_SHORT).show();
                                        FirebaseAuth.getInstance().signOut();
                                        startActivity(new Intent(EditPasswordActivity.this, LoginActivity.class));
                                        finish();
                                        progressDialog.dismiss();
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(EditPasswordActivity.this, "Terjadi Kesalahan, Silakan Coba Lagi", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(getApplicationContext(), "Password tidak sama!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Email salah!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(EditPasswordActivity.this, ProfileActivity.class));
        finish();
    }
}