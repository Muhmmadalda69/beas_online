package com.taruma.beas.model;

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
    private String skor_6;
    private String skor_7;
    private String skor_8;
    private String skor_9;
    private String skor_10;
    private String total_skor = skor_1 + skor_2 + skor_3 + skor_4 + skor_5 + skor_6 +
            skor_7 + skor_8 + skor_9 + skor_10;

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

    public String getSkor_6() {
        return skor_6;
    }

    public void setSkor_6(String skor_6) {
        this.skor_6 = skor_6;
    }

    public String getSkor_7() {
        return skor_7;
    }

    public void setSkor_7(String skor_7) {
        this.skor_7 = skor_7;
    }

    public String getSkor_8() {
        return skor_8;
    }

    public void setSkor_8(String skor_8) {
        this.skor_8 = skor_8;
    }

    public String getSkor_9() {
        return skor_9;
    }

    public void setSkor_9(String skor_9) {
        this.skor_9 = skor_9;
    }

    public String getSkor_10() {
        return skor_10;
    }

    public void setSkor_10(String skor_10) {
        this.skor_10 = skor_10;
    }

    public String getTotal_skor() {
        return total_skor = skor_1+skor_2+skor_3+skor_4+skor_5 + skor_6 +
                skor_7 + skor_8 + skor_9 + skor_10;
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
                " "+skor_6 +"\n" +
                " "+skor_7 +"\n" +
                " "+skor_8 +"\n" +
                " "+skor_9 +"\n" +
                " "+skor_10 +"\n" +
                " "+total_skor;
    }

    public Nilai(String userName, String skor){
        namaUser = userName;
        skor_1 = skor;
        skor_2 = skor;
        skor_3 = skor;
        skor_4 = skor;
        skor_5 = skor;
        skor_6 = skor;
        skor_7 = skor;
        skor_8 = skor;
        skor_9 = skor;
        skor_10 = skor;
    }
}