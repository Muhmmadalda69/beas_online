package com.example.beas;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beas.model.Nilai;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class L3Activity extends AppCompatActivity {

    TextView tv_skor, tv_soalKe;
    ImageView iv_huruf;
    EditText et_jawaban;
    Button bt_jawab;
    int x ;
    int arr=10;
    int skor, soalKe = 1;
    String jawaban;
    String jumlahSkor;
    //ubah
    DBSoal soal = new DBSoal();

    //FIREBASE
    public static final String EXTRA_NILAI3 = "extra_nilai3";
    private Nilai nilai3;
    DatabaseReference database;
    FirebaseUser firebaseUser;

    private TextView timer;
    private TimerClass timerClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ubah
        setContentView(R.layout.activity_l3);

        //FIREBASE
        database = FirebaseDatabase.getInstance().getReference("db_skor");
        nilai3 = new Nilai();
        nilai3 = getIntent().getParcelableExtra(EXTRA_NILAI3);

        tv_skor = findViewById(R.id.tv_skor);
        tv_soalKe = findViewById(R.id.tv_soalKe);
        iv_huruf = findViewById(R.id.iv_huruf);
        et_jawaban = findViewById(R.id.et_jawaban);
        bt_jawab = findViewById(R.id.bt_jawab);


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
                soalKe = soalKe + 1;
                tv_soalKe.setText("Soal " + soalKe);
                tv_skor.setText(""+skor);
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

        }else {
            ubahGambar();
        }
        x++;
    }

    private void ubahGambar() {
        Resources res = getResources();
        //ubah
        jawaban = soal.getPertanyaan_tiga(x);
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

    //FIREBASE
    private void Selesai() {
        jumlahSkor = String.valueOf(skor);    //menjadikan skor menjadi string

        //ubah alert
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("GAME SELESAI");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Nilai Kamu : " + jumlahSkor)
                .setIcon(R.mipmap.ic_logo)
                .setCancelable(false)
                .setPositiveButton("Ok", (dialog, id) -> {
                    //menyimpan ke realtime firebase
                    skorMinim();
                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        String namaUser = Objects.requireNonNull(firebaseUser).getDisplayName();
                        nilai3 = new Nilai();
                        submitSkor(new Nilai(namaUser, jumlahSkor));
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }
    private void skorMinim() {
        int SkorMinim = Integer.parseInt(jumlahSkor);
        if(SkorMinim >= 70){
            Toast.makeText(L3Activity.this, "Anda bisa lanjut ke LEVEL 4", Toast.LENGTH_LONG).show();

            SharedPreferences preferences = getSharedPreferences("PREFS3",0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("SkorLulus3", SkorMinim);
            editor.apply();

            Intent intent = new Intent(getApplicationContext(),TebakActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(L3Activity.this, "Minimal skor untuk lanjut level 4 adalah \b 70", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    //FIREBASE
    private void updateNilai() {
        database.child("skor 3") //akses parent index, ibaratnya seperti nama tabel
                .setValue(jumlahSkor);
    }

    private void submitSkor(Nilai nilai_3) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        //ubah
        nilai3.setSkor_3(jumlahSkor);
        database.child(nilai_3.getNamaUser()).child("level 3").setValue(nilai_3.getSkor_3());
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
                    L3Activity.this.finish();
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