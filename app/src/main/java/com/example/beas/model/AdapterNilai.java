package com.example.beas.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beas.R;

import java.util.List;

public class AdapterNilai extends RecyclerView.Adapter<AdapterNilai.ViewHolder> {

    Context context;
    List<Nilai> list;

    public AdapterNilai(Context context, List<Nilai> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterNilai.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNilai.ViewHolder holder, int position) {
        holder.listNama.setText(list.get(position).getNamaUser());
        holder.listNama.setText(list.get(position).getTotal_skor());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView listNama, listSkor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            listNama = itemView.findViewById(R.id.listNama);
            listSkor = itemView.findViewById(R.id.listSkor);
        }
    }
}
