package com.example.beas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;


public class EditProfileActivity extends AppCompatActivity{
    private Button bt_save, bt_batal;
    ProgressDialog progressDialog;
    private ImageView profileImageView;
    private TextView profileChangeBtn;
    private FirebaseUser firebaseUser;

    final int kodeGallery = 100, kodeKamera = 99;
    Uri imageUri;

    // Inside your onCreate method
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profileChangeBtn = findViewById(R.id.change_profile_btn);
        profileImageView =findViewById(R.id.foto_akun);
        bt_save = findViewById(R.id.bt_save);
        bt_batal = findViewById(R.id.bt_batal);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {
            profileImageView.setImageURI(firebaseUser.getPhotoUrl());

        }


        progressDialog = new ProgressDialog(EditProfileActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan tunggu...");
        progressDialog.setCancelable(false);

        profileImageView = findViewById(R.id.foto_akun);


        bt_save.setOnClickListener(view -> {
            progressDialog.show();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            UserProfileChangeRequest profileUpdates = null;
            if (imageUri != null) {
                profileUpdates = new UserProfileChangeRequest.Builder()
                        .setPhotoUri(imageUri)
                        .build();

            } else {
                profileUpdates = new UserProfileChangeRequest.Builder()
                        .setPhotoUri(firebaseUser.getPhotoUrl())
                        .build();
            }
            assert user != null;
            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                StorageReference photoRef = storageReference.child("profile_photos/" + firebaseUser.getDisplayName() + ".jpg");
                                photoRef.putFile(imageUri).addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        // Setelah upload selesai, dapatkan URL gambar dari Firebase Storage
                                        photoRef.getDownloadUrl().addOnSuccessListener(uri -> {
                                            // Uri ini berisi URL gambar yang dapat Anda gunakan
                                            String imageUrl = uri.toString();

                                            // Update the Realtime Database with the photo URL
                                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Foto_profil");
                                            Map<String, Object> updateMap = new HashMap<>();
                                            updateMap.put(firebaseUser.getDisplayName(), imageUrl);
                                            databaseReference.updateChildren(updateMap);

                                            Toast.makeText(EditProfileActivity.this, "Foto Diubah", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(EditProfileActivity.this, ProfileActivity.class));
                                            progressDialog.dismiss();
                                            finish();
                                        }).addOnFailureListener(e -> {
                                            // Handle error getting download URL
                                            Toast.makeText(EditProfileActivity.this, "Gagal mendapatkan URL gambar", Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                        });
                                    }
//                                   photoRef.getDownloadUrl();
//                                    // Update the Realtime Database with the photo URL
//                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Foto_profil");
//                                    Map<String, Object> updateMap = new HashMap<>();
//                                    updateMap.put(user.getDisplayName(), photoRef.toString());
//                                    databaseReference.updateChildren(updateMap);
//                                    Toast.makeText(EditProfileActivity.this, "Foto Diubah", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(EditProfileActivity.this, ProfileActivity.class));
//                                    progressDialog.dismiss();
//                                    finish();
                                });
                            }
                        }
                    });
        });

        profileChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentGallery, kodeGallery);
            }

        });

        bt_batal.setOnClickListener(View -> {
            startActivity(new Intent(EditProfileActivity.this, ProfileActivity.class));
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == kodeGallery && resultCode == RESULT_OK) {
            imageUri = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(imageUri,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            profileImageView.setImageURI(imageUri);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(EditProfileActivity.this, ProfileActivity.class));
        finish();
    }
}