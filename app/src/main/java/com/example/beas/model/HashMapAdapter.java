package com.example.beas.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.beas.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class HashMapAdapter extends ArrayAdapter<HashMap<String, String>> {
    private final Context context;
    private final List<HashMap<String, String>> dataList;
    public HashMapAdapter(Context context, List<HashMap<String, String>> dataList) {
        super(context, R.layout.row_data, dataList);
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.row_data, parent, false);

        TextView listNomor = (TextView) rowView.findViewById(R.id.listNomor);
        ImageView listFoto = (ImageView) rowView.findViewById(R.id.foto_akun);
        TextView listNama = (TextView) rowView.findViewById(R.id.listNama);
        TextView listSkor = (TextView) rowView.findViewById(R.id.listSkor);

        listNomor.setText((position+1)+".");
        HashMap<String, String> data = dataList.get(position);

        // Mengambil nama pengguna dan mengonkatenasi dengan koleksi "Foto_profil"
        String username = data.get("nama");
        String photoUrl = "https://console.firebase.google.com/u/0/project/beas-dfb0e/database/beas-dfb0e-default-rtdb/data/~2F/Foto_profil/" + username + ".jpg";

        // Memuat foto menggunakan Picasso
        Picasso.get().load(photoUrl).into(listFoto);


        listNama.setText(data.get("nama"));
        listSkor.setText(data.get("totalSkor"));
        return rowView;
    }
}
