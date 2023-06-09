package com.example.beas;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.AutoText;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beas.model.AdapterNilai;
import com.example.beas.model.HashMapAdapter;
import com.example.beas.model.Nilai;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

    ListView listView;
    TextView mySkor;
    String level1,level2,level3,level4,level5;
    Nilai nilai;
    int totalNilai = 0;

    private List<HashMap<String, String>> dataList = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_skor);

        listView = findViewById(R.id.tv_skor);
        mySkor = findViewById(R.id.mySkor);


        dbSkorRef.child(namaUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot nilaiSnapshot : snapshot.getChildren()) {
                    level1 = nilaiSnapshot.getValue(String.class);
                    assert level1 != null;
                    totalNilai += Integer.parseInt(level1);
                }
                mySkor.setText(namaUser + " - " + totalNilai);
                dbTotal.child(namaUser).setValue(totalNilai);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    protected void onStart(){
        super.onStart();
        dbTotal.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataList.clear();
                //tampil dari besar ke kecil
                Map<String, Long> data = (Map<String, Long>) dataSnapshot.getValue();

                List<Map.Entry<String, Long>> list = new ArrayList<>(data.entrySet());
                Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
                    @Override
                    public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });

                for (Map.Entry<String, Long> entry : list) {
                    HashMap<String, String> data1 = new HashMap<>();
                    data1.put("nama", entry.getKey());
                    data1.put("totalSkor", entry.getValue().toString());
                    dataList.add(data1);
                }
                // Find the ListView and set the adapter
                ListView listView = (ListView) findViewById(R.id.tv_skor);
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