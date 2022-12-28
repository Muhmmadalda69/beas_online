package com.example.beas.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by Herdi_WORK on 18.06.17.
 */

@IgnoreExtraProperties
public class Nilai implements Serializable{

    private String key;
    private String skor_1;
    private String skor_2;
    private String skor_3;

    public Nilai(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    @Override
    public String toString() {
        return " "+key+"\n" +
                " "+skor_1 +"\n" +
                " "+skor_2 +"\n" +
                " "+skor_3;
    }

    public Nilai(String skor1){
        skor_1 = skor1;
    }
}