package com.example.beas;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beas.helper.DBHelper;

import java.util.concurrent.TimeUnit;

public class L1Activity extends AppCompatActivity {

    DBHelper helper;
    TextView tv_skor, tv_soalKe;
    ImageView iv_huruf;
    EditText et_jawaban;
    Button bt_jawab;
    int x ;
    int arr=10;
    int skor, soalKe = 1;
    String jawaban;
    String jumlahSkor;

    DBSoal soall1 = new DBSoal();

    private TextView timer;
    private TimerClass timerClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l1);

        tv_skor = findViewById(R.id.tv_skor);
        tv_soalKe = findViewById(R.id.tv_soalKe);
        iv_huruf = findViewById(R.id.iv_huruf);
        et_jawaban = findViewById(R.id.et_jawaban);
        bt_jawab = findViewById(R.id.bt_jawab);

        helper = new DBHelper(this);

        timer = findViewById(R.id.tv_timer);

        setKonten();

        bt_jawab.setOnClickListener(view -> cekJawaban());

        //Set Waktu selama 1 detik = 60000 millis dengan interval 1 detik = 1000 millis
        timerClass = new TimerClass(60000, 1000);

    }

    @SuppressLint("SetTextI18n")
    public void cekJawaban() {
        if(!et_jawaban.getText().toString().isEmpty()){ //jika edit text TIDAK kosong
            //jika text yang tertulis di edit text tsb = nilai dari var jawaban
            if(et_jawaban.getText().toString().equalsIgnoreCase(jawaban)){
                skor = skor + 10;
                soalKe = soalKe + 1;
                tv_soalKe.setText("Soal " + soalKe);
                tv_skor.setText(""+skor);	//mtvSkor2 diset nilainya = var skor
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
                setKonten(); //update next konten
            }else{
                tv_skor.setText(""+skor);
                soalKe = soalKe + 1;
                tv_soalKe.setText("Soal " + soalKe);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                setKonten();
            }
        }else{
            Toast.makeText(this, "Silahkan pilih jawaban dulu!", Toast.LENGTH_SHORT).show();
        }
    }


    private void setKonten() {
            et_jawaban.setText(null);
        if(x >= arr) { //jika nilai x melebihi nilai arr(panjang array) maka akan pindah activity (kuis selesai)

            //waktu diaskhiri
            timerClass.cancel();
            Selesai();

            }else ubahGambar();
            x++;
    }

    private void ubahGambar() {
        Resources res = getResources();
        jawaban = soall1.getPertanyaan_satu(x);
        String mPhoto = jawaban;
        int resID = res.getIdentifier(mPhoto, "drawable", getPackageName());
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable drawable = res.getDrawable(resID);
        iv_huruf.setImageDrawable(drawable);
    }


    //Membuat InnerClass untuk konfigurasi Countdown Time
    public class TimerClass extends CountDownTimer {

        TimerClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //Method ini berjalan saat waktu/timer berubah
        @Override
        public void onTick(long millisUntilFinished) {
            //Kenfigurasi Format Waktu yang digunakan
            @SuppressLint("DefaultLocale") String waktu = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            //Menampilkannya pada TexView
            timer.setText(waktu);

        }

            @Override
            public void onFinish() {
                //waktu diaskhiri
                timerClass.cancel();
                Selesai();
        }
    }

    private void Selesai() {
        jumlahSkor = String.valueOf(skor);    //menjadikan skor menjadi string

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(L1Activity.this);
        alertDialog.setTitle(" Nilai Kamu : " + jumlahSkor);
        final EditText inputNama = new EditText(L1Activity.this);
        inputNama.setHint("Masukan Nama Kamu");
        inputNama.setInputType(InputType.TYPE_CLASS_TEXT);
        alertDialog.setView(inputNama);

        alertDialog.setPositiveButton("Ok", (dialogInterface, i) -> {
            String namaPemain = inputNama.getText().toString();

            ContentValues values = new ContentValues();
            values.put(DBHelper.row_nama,namaPemain);
            values.put(DBHelper.row_skor1,jumlahSkor);
            //menyimpan data
            helper.insertData(values);

            int SkorMinim = Integer.parseInt(jumlahSkor);
            if(SkorMinim >= 70){
                Toast.makeText(L1Activity.this, "Anda bisa lanjut ke LEVEL 2", Toast.LENGTH_LONG).show();

                SharedPreferences preferences = getSharedPreferences("PREFS",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("SkorLulus", SkorMinim);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(),TebakActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(L1Activity.this, "Minimal skor untuk lanjut level 2 adalah \b 70", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        alertDialog.show();
    }

    public void onStart() {
        super.onStart();
        timerClass.start();
    }

    public void onBackPressed(){
        showDialog();
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("mau Keluar dari game?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Klik Ya untuk keluar!")
                .setIcon(R.mipmap.ic_logo)
                .setCancelable(false)
                .setPositiveButton("Ya", (dialog, id) -> {
                    // jika tombol diklik, maka akan menutup activity ini
                    L1Activity.this.finish();
                    timerClass.cancel();
                })
                .setNegativeButton("Tidak", (dialog, id) -> {
                    // jika tombol ini diklik, akan menutup dialog
                    // dan tidak terjadi apa2
                    dialog.cancel();
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }
}