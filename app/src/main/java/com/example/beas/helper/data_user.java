package com.example.beas.helper;

public class data_user {
    private String username;
    private String alamat, id;

    //Membuat Method Getter (Wajib), Untuk mendapatkan data NIM, Nama dan Jurusan

    public String getNama() {
        return username;
    }
    public String getAlamat() {
        return alamat;
    }

    //Membuat Konstuktor kosong untuk membaca data snapshot
    public data_user(){

    }

    public data_user(String username, String alamat) {
        this.username = username;
        this.alamat = alamat;
    }
}
