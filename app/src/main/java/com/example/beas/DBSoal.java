package com.example.beas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DBSoal {

    //soal level 1
        Set<String> swara = new HashSet<>(Arrays.asList(
                "a",
            "i",
            "u",
            "é",
            "o",
            "eu",
            "e")
        );
        List<String> list_swara = new ArrayList<>(swara);

        //membuat getter untuk mengambil jawaban benar
        public String getPertanyaan_satu(int idx) {
            int size = list_swara.size();
            idx = new Random().nextInt(size);
            String random = list_swara.get(idx);
            return random;
        }// end getPertanyaan_satu

    //soal level 2
    Set<String> ngalagenaSerapan = new HashSet<>(Arrays.asList(
            "fa",
            "qa",
            "va",
            "xa",
            "za")
    );
    List<String> list_ngalagenaSerapan = new ArrayList<>(ngalagenaSerapan);

    //membuat getter untuk mengambil jawaban benar
    public String getPertanyaan_dua(int idx) {
        int size = list_ngalagenaSerapan.size();
        idx = new Random().nextInt(size);
        String random = list_ngalagenaSerapan.get(idx);
        return random;
    }// end getPertanyaan_dua

    //soal level 3
    Set<String> angka = new HashSet<>(Arrays.asList(
            "hiji",
            "dua",
            "tilu",
            "opat",
            "lima",
            "genep",
            "tujuh",
            "dalapan",
            "salapan",
            "enol")
    );
    List<String> list_angka = new ArrayList<>(angka);

    //membuat getter untuk mengambil jawaban benar
    public String getPertanyaan_tiga(int idx) {
        int size = list_angka.size();
        idx = new Random().nextInt(size);
        String random = list_angka.get(idx);
        return random;
    }// end getPertanyaan_angka

    //soal level 4
    Set<String> ngalagena = new HashSet<>(Arrays.asList(
            "ka",
            "ga",
            "nga",
            "ca",
            "ja",
            "nya",
            "ta",
            "da",
            "na",
            "pa",
            "ba",
            "ma",
            "ya",
            "ra",
            "la",
            "Wa",
            "sa",
            "ha")
    );
    List<String> list_ngalagena = new ArrayList<>(ngalagena);

    //membuat getter untuk mengambil jawaban benar
    public String getPertanyaan_empat(int idx) {
        int size = list_ngalagena.size();
        idx = new Random().nextInt(size);
        String random = list_ngalagena.get(idx);
        return random;
    }// end getPertanyaan_empat

    //soal level 5
    Set<String> rangken = new HashSet<>(Arrays.asList(
            "kang",
            "kar",
            "ke",
            "keu",
            "ki",
            "kla",
            "kra",
            "ku",
            "k",
            "kah",
            "ke_kutip",
            "ko",
            "kya",
            "ah",
            "ang",
            "ar")
    );
    List<String> list_rangken = new ArrayList<>(rangken);

    //membuat getter untuk mengambil jawaban benar
    public String getPertanyaan_lima(int idx) {
        int size = list_rangken.size();
        idx = new Random().nextInt(size);
        String random = list_rangken.get(idx);
        return random;
    }// end getPertanyaan_lima

}//end class




