package com.example.beas.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Nilai implements Parcelable {
    private String id;
    private String nim;
    private String nama;

    public Nilai() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.nim);
        dest.writeString(this.nama);
    }

    protected Nilai(Parcel in) {
        this.id = in.readString();
        this.nim = in.readString();
        this.nama = in.readString();
    }

    public static final Parcelable.Creator<Nilai> CREATOR = new Parcelable.Creator<Nilai>() {
        @Override
        public Nilai createFromParcel(Parcel source) {
            return new Nilai(source);
        }

        @Override
        public Nilai[] newArray(int size) {
            return new Nilai[size];
        }
    };
}
