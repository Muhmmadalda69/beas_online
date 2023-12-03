package com.example.beas.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.beas.R;

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
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.row_data, parent, false);

        TextView listNomor = (TextView) rowView.findViewById(R.id.listNomor);
        ImageView listFoto = (ImageView) rowView.findViewById(R.id.foto_akun);
        TextView listNama = (TextView) rowView.findViewById(R.id.listNama);
        TextView listSkor = (TextView) rowView.findViewById(R.id.listSkor);

        listNomor.setText((position+1)+".");
        HashMap<String, String> data = dataList.get(position);

        // Mengambil nama pengguna dan mengonkatenasi dengan koleksi "Foto_profil"
        String photoUrl = data.get("fotoUrl");

// Periksa apakah photoUrl tidak kosong sebelum memuat dengan Picasso
        if (!TextUtils.isEmpty(photoUrl)) {
            // Memuat foto menggunakan Picasso
            Glide.with(context).load(photoUrl).into(listFoto);
        } else {
            // Handle kasus ketika photoUrl kosong
            // Misalnya, set default image atau tampilkan pesan kesalahan
            listFoto.setImageResource(R.drawable.profile_male); // Gantilah dengan sumber daya gambar default
        }



        listNama.setText(data.get("nama"));
        listSkor.setText(data.get("totalSkor"));
        return rowView;
    }
}
