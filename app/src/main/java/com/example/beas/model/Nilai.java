package com.example.beas.model;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by Herdi_WORK on 18.06.17.
 */

@IgnoreExtraProperties
public class Nilai implements Serializable{

    private String namaUser;
    private String skor_1;
    private String skor_2;
    private String skor_3;
    private String skor_4;
    private String skor_5;
    private String total_skor = skor_1 + skor_2 + skor_3 + skor_4 + skor_5;

    public Nilai(){

    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getSkor_1() {
        return skor_1;
    }

    public void setSkor_1(String skor_1) {
        this.skor_1 = skor_1;
    }
    public String getSkor_2() {
        return skor_2;
    }

    public void setSkor_2(String skor_2) {
        this.skor_2 = skor_2;
    }

    public String getSkor_3() {
        return skor_3;
    }

    public void setSkor_3(String skor_3) {
        this.skor_3 = skor_3;
    }

    public String getSkor_4() {
        return skor_4;
    }

    public void setSkor_4(String skor_4) {
        this.skor_4 = skor_4;
    }

    public String getSkor_5() {
        return skor_5;
    }

    public void setSkor_5(String skor_5) {
        this.skor_5 = skor_5;
    }


    public String getTotal_skor() {
        return total_skor;
    }

    public void setTotal_skor(String total_skor) {
        this.total_skor = total_skor;
    }

    @Override
    public String toString() {
        return " "+namaUser+"\n" +
                " "+skor_1 +"\n" +
                " "+skor_2 +"\n" +
                " "+skor_3 +"\n" +
                " "+skor_4 +"\n" +
                " "+skor_5 +"\n" +
                " "+total_skor;
    }

    public Nilai(String userName, String skor){
        namaUser = userName;
        skor_1 = skor;
        skor_2 = skor;
        skor_3 = skor;
        skor_4 = skor;
        skor_5 = skor;
    }
}