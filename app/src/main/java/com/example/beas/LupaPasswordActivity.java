package com.example.beas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LupaPasswordActivity extends AppCompatActivity {
    private Button bt_kirim;
    private EditText et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        et_email = findViewById(R.id.et_email);
        bt_kirim = findViewById(R.id.bt_kirim);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = et_email.getText().toString();

        bt_kirim.setOnClickListener(view -> {
            if(et_email.getText().toString().isEmpty()){
                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LupaPasswordActivity.this,
                                            "Reset password dikirim ke Email anda " +
                                                    "/n jika tidak ada coba cek di SPAM",
                                            Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(LupaPasswordActivity.this,
                                            "Terjadi Kesalahan, coba lagi!",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }else{
                Toast.makeText(LupaPasswordActivity.this,
                        "Masukan Email dulu!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}