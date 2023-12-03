package com.taruma.beas.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.taruma.beas.R;

import java.util.ArrayList;

public class AdapterNilai extends BaseAdapter {

    Context context;
    ArrayList<Nilai> list = new ArrayList<>();

    public void setNilaiArrayList(ArrayList<Nilai> nilaiArrayList) {
        this.list = nilaiArrayList;
    }

    public AdapterNilai(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;

        if (itemView == null) {
            itemView = LayoutInflater.from(context)
                    .inflate(R.layout.row_data, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);

        Nilai nilai = (Nilai) getItem(i);
        viewHolder.bind(nilai);
        return itemView;
    }

    private class ViewHolder {
        private TextView listNama, listSkor;

        ViewHolder(View view) {
            listNama = view.findViewById(R.id.listNama);
            listSkor = view.findViewById(R.id.listSkor);
        }

        void bind(Nilai nilai) {
            listNama.setText(nilai.getNamaUser());
            listSkor.setText(nilai.getTotal_skor());
        }
    }
}
