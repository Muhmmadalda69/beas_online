package com.example.beas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beas.helper.CustomCursorAdapter;
import com.example.beas.helper.DBHelper;

import java.util.ArrayList;

public class LihatSkorActivity extends AppCompatActivity {
    TextView tv_skor;

    ListView listView;
    DBHelper helper;
    private SQLiteDatabase dataBase;

    private final ArrayList<String> list_id = new ArrayList<>();
    private final ArrayList<String> list_Name = new ArrayList<>();
    private final ArrayList<String> list_Skor = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_skor);
        tv_skor = findViewById(R.id.tv_skor);

        helper = new DBHelper(this);
        listView = findViewById(R.id.list_skor1);


        Cursor cursor = helper.allData();
        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor, 1);
        listView.setAdapter(customCursorAdapter);

//menekan lama item
        listView.setOnItemLongClickListener((arg0, arg1, arg2, arg3) -> {

            //menampillkan alert dialog
            AlertDialog.Builder build;
            build = new AlertDialog.Builder(LihatSkorActivity.this);
            build.setTitle("Menghapus data " + list_Name.get(arg2));
            build.setMessage("Yakin menghapus data? \n" +
                    "klik batal untuk membatalkan");

            //memilih hapus
            build.setPositiveButton("Hapus",
                    (dialog, which) -> {
                        dataBase.delete(
                                DBHelper.table_name,
                                DBHelper.row_id + "="
                                        + list_id.get(arg2), null);
                        Toast.makeText(
                                getApplicationContext(),
                                "Data " + list_Name.get(arg2) +
                                        " dihapus", Toast.LENGTH_SHORT).show();
                        displayData();
                        dialog.cancel();
                    });//end DELETE
            //memilih Batal
            build.setNegativeButton("Batal",
                    (dialog, which) -> dialog.cancel());//end BATAL DELETE
            AlertDialog alert = build.create();
            alert.show();

            return true;
        });//end setOnItemLongClickListener


    }// end onCreate method

    @Override
    protected void onResume() {
        //menampilkan screen baru
        displayData();
        Toast.makeText(getApplicationContext(), "Tekan dan tahan item\n " +
                "untuk menghapus item", Toast.LENGTH_LONG).show();
        super.onResume();
    }

    /**
     * menampilkan data dari MYSQLite
     */
    @SuppressLint("Range")
    private void displayData() {
        dataBase = helper.getWritableDatabase();
        //the SQL command to fetched all records from the table
        @SuppressLint("Recycle") Cursor mCursor = dataBase.rawQuery("SELECT * FROM "
                + DBHelper.table_name, null);

        //reset variables
        list_id.clear();
        list_Name.clear();
        list_Skor.clear();

        //fetch each record
        if (mCursor.moveToFirst()) {
            do {
                //get data from field
                list_id.add(mCursor.getString(mCursor.getColumnIndex(DBHelper.row_id)));
                list_Name.add(mCursor.getString(mCursor.getColumnIndex(DBHelper.row_nama)));
                list_Skor.add(mCursor.getString(mCursor.getColumnIndex(DBHelper.row_skor1)));

            } while (mCursor.moveToNext());
            //do above till data exhausted
        }

        //display to screen
        Cursor cursor = helper.allData();
        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor, 1);
        listView.setAdapter(customCursorAdapter);
    }//end displayData

}//end DisplayList class