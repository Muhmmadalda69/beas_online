package com.example.beas.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.beas.R;

import java.util.HashMap;
import java.util.List;

public class HashMapAdapter extends ArrayAdapter<HashMap<String, String>> {
    private Context context;
    private List<HashMap<String, String>> dataList;
    public HashMapAdapter(Context context, List<HashMap<String, String>> dataList) {
        super(context, R.layout.row_data, dataList);
        this.context = context;
        this.dataList = dataList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_data, parent, false);
        TextView listNama = (TextView) rowView.findViewById(R.id.listNama);
        TextView listSkor = (TextView) rowView.findViewById(R.id.listSkor);
        HashMap<String, String> data = dataList.get(position);
        listNama.setText(data.get("nama"));
        listSkor.setText(data.get("totalSkor"));
        return rowView;
    }
}
