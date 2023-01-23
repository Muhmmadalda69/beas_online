package com.example.beas;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beas.model.AdapterNilai;
import com.example.beas.model.Nilai;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LihatSkorActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbSkorRef = database.getReference("db_skor");
    ListView listView;
    Nilai nilai;

    private ArrayList<Nilai> nilaiArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_skor);

        listView = findViewById(R.id.tv_skor);
        nilaiArrayList = new ArrayList<>();

    }


    protected void onStart(){
        super.onStart();
        dbSkorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nilaiArrayList.clear();
                for (DataSnapshot nilaiSnapshot : dataSnapshot.getChildren()) {
                    Nilai nilai = nilaiSnapshot.getValue(Nilai.class);
                    nilaiArrayList.add(nilai);
                }

                AdapterNilai adapter = new AdapterNilai(LihatSkorActivity.this);
                adapter.setNilaiArrayList(nilaiArrayList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LihatSkorActivity.this, "Terjadi kesalahan.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}//end DisplayList class