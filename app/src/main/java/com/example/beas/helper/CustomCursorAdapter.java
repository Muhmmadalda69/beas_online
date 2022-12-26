package com.example.beas.helper;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.beas.R;

public class CustomCursorAdapter extends CursorAdapter {

    private final LayoutInflater layoutInflater;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CustomCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View v = layoutInflater.inflate(R.layout.row_data, viewGroup, false);
        MyHolder holder = new MyHolder();
        holder.ListID = v.findViewById(R.id.listID);
        holder.ListNama = v.findViewById(R.id.listNama);
        holder.ListSkor = v.findViewById(R.id.listSkor);
        v.setTag(holder);
        return v;
    }

    @SuppressLint("Range")
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        MyHolder holder = (MyHolder)view.getTag();

        holder.ListID.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_id)));
        holder.ListSkor.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_skor1)));
        holder.ListNama.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_nama)));
    }

    static class MyHolder{
        TextView ListID;
        TextView ListSkor;
        TextView ListNama;
    }
}