package com.example.beas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beas.model.HashMapAdapter;
import com.example.beas.model.Nilai;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LihatSkorActivity extends AppCompatActivity {

    //FirebaseUser firebaseUser;

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    String namaUser = Objects.requireNonNull(firebaseUser).getDisplayName();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbSkorRef = database.getReference("db_skor");
    DatabaseReference dbTotal = database.getReference("db_total");
    DatabaseReference dbFoto = database.getReference("Foto_profil");

    ListView listView;
    TextView mySkor;
    String level1,level2,level3,level4,level5;
    Nilai nilai;
    int totalNilai = 0;

    private List<HashMap<String, String>> dataList = new ArrayList<>();
    private Map<String, String> dataFoto = new HashMap<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_skor);

        listView = findViewById(R.id.tv_skor);
        mySkor = findViewById(R.id.mySkor);

        dbFoto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshotFoto) {
                // Bersihkan dataFoto sebelum mengisinya
                dataFoto.clear();

                // Ambil data foto dari snapshot
                for (DataSnapshot fotoSnapshot : snapshotFoto.getChildren()) {
                    String namaPengguna = fotoSnapshot.getKey();
                    String fotoValue = fotoSnapshot.getValue().toString();

                    // Tambahkan data foto ke dalam dataFoto
                    dataFoto.put(namaPengguna, fotoValue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled
            }
        });


    }

    // Tambahkan metode berikut di dalam kelas Anda
    private void hitungTotalNilai(DataSnapshot snapshot) {
        totalNilai = 0;
        for (DataSnapshot nilaiSnapshot : snapshot.getChildren()) {
            level1 = nilaiSnapshot.getValue(String.class);
            assert level1 != null;
            totalNilai += Integer.parseInt(level1);
        }
        mySkor.setText(namaUser + " - " + totalNilai);
        dbTotal.child(namaUser).setValue(totalNilai);
    }


    protected void onStart(){
        super.onStart();

        dbSkorRef.child(namaUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hitungTotalNilai(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled
            }
        });

        dbTotal.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataList.clear();
                //tampil dari besar ke kecil
                Map<String, Long> data = (Map<String, Long>) dataSnapshot.getValue();

                List<Map.Entry<String, Long>> list = new ArrayList<>(data.entrySet());
                Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

                for (Map.Entry<String, Long> entry : list) {
                    String nama = entry.getKey();
                    Long totalSkor = entry.getValue();

                    // Ambil URL foto dari dbFoto
                    String fotoUrl = "";
                    if (dataFoto.containsKey(nama)) {
                        fotoUrl = dataFoto.get(nama).toString();
                    }

                    HashMap<String, String> data1 = new HashMap<>();
                    data1.put("nama", nama);
                    data1.put("totalSkor", totalSkor.toString());
                    data1.put("fotoUrl", fotoUrl);
                    dataList.add(data1);
                }

                // Set adapter
                ListView listView = findViewById(R.id.tv_skor);
                HashMapAdapter adapter = new HashMapAdapter(LihatSkorActivity.this, dataList);
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LihatSkorActivity.this, "Terjadi kesalahan.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}//end DisplayList class