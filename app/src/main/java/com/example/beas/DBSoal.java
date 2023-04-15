package com.example.beas;

import com.example.beas.model.ShuffleRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DBSoal {
    Random rando = new Random();

    ShuffleRandom rand = new ShuffleRandom();

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
            for (int i = size; i > 1; i--) {
                idx = rand.shuffleList(i);
                Collections.swap(list_swara, i - 1, idx);
            }
            String random = list_swara.get(idx);
            return random;
        }// end getPertanyaan_satu

    //soal level 2
    Set<String> ngalagenaSerapan = new HashSet<>(Arrays.asList(
            "fa",
            "qa",
            "va",
            "xa",
            "za",
            "sya",
            "kha")
    );
    List<String> list_ngalagenaSerapan = new ArrayList<>(ngalagenaSerapan);

    //membuat getter untuk mengambil jawaban benar
    public String getPertanyaan_dua(int idx) {
        int size = list_ngalagenaSerapan.size();
        for (int i = size; i > 1; i--) {
            idx = rand.shuffleList(i);
            Collections.swap(list_ngalagenaSerapan, i - 1, idx);
        }
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
        for (int i = size; i > 1; i--) {
            idx = rand.shuffleList(i);
            Collections.swap(list_angka, i - 1, idx);
        }
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
        for (int i = size; i > 1; i--) {
            idx = rand.shuffleList(i);
            Collections.swap(list_ngalagena, i - 1, idx);
        }
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
            "ke_aksen",
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
        for (int i = size; i > 1; i--) {
            idx = rand.shuffleList(i);
            Collections.swap(list_rangken, i - 1, idx);
        }
        String random = list_rangken.get(idx);
        return random;
    }// end getPertanyaan_lima

    //soal level 6
    Set<String> level6 = new HashSet<>(Arrays.asList(
            "aya",
            "kaca",
            "maca",
            "mana",
            "mata",
            "mawa",
            "naga",
            "raja",
            "rawa",
            "saha")
    );
    List<String> list_level6 = new ArrayList<>(level6);

    //membuat getter untuk mengambil jawaban benar
    public String getPertanyaan_enam(int idx) {
        int size = list_level6.size();
        for (int i = size; i > 1; i--) {
            idx = rand.shuffleList(i);
            Collections.swap(list_level6, i - 1, idx);
        }
        String random = list_level6.get(idx);
        return random;
    }// end getPertanyaan_enam

    //soal level 7
    Set<String> level7 = new HashSet<>(Arrays.asList(
            "batu",
            "calana",
            "carita",
            "gagang",
            "gali",
            "kayu",
            "mawar",
            "mirasa",
            "ratu",
            "sapu")
    );
    List<String> list_level7 = new ArrayList<>(level7);

    //membuat getter untuk mengambil jawaban benar
    public String getPertanyaan_tujuh(int idx) {
        int size = list_level7.size();
        for (int i = size; i > 1; i--) {
            idx = rand.shuffleList(i);
            Collections.swap(list_level7, i - 1, idx);
        }
        String random = list_level7.get(idx);
        return random;
    }// end getPertanyaan_tujuh

    //soal level 8
    Set<String> level8 = new HashSet<>(Arrays.asList(
            "jawara",
            "karawang",
            "makalang",
            "nakula",
            "rahwana",
            "ramayana",
            "saha",
            "sakola",
            "sapatu",
            "tandang")
    );
    List<String> list_level8 = new ArrayList<>(level8);

    //membuat getter untuk mengambil jawaban benar
    public String getPertanyaan_dalapan(int idx) {
        int size = list_level8.size();
        for (int i = size; i > 1; i--) {
            idx = rand.shuffleList(i);
            Collections.swap(list_level8, i - 1, idx);
        }
        String random = list_level8.get(idx);
        return random;
    }// end getPertanyaan_dalapan

    //soal level 9
    Set<String> level9 = new HashSet<>(Arrays.asList(
            "brebes",
            "hirup",
            "hordéng",
            "kasur",
            "kéong",
            "merdéka",
            "prabu",
            "priangan",
            "sendal",
            "téras")
    );
    List<String> list_level9 = new ArrayList<>(level9);

    //membuat getter untuk mengambil jawaban benar
    public String getPertanyaan_salapan(int idx) {
        int size = list_level9.size();
        for (int i = size; i > 1; i--) {
            idx = rand.shuffleList(i);
            Collections.swap(list_level9, i - 1, idx);
        }
        String random = list_level9.get(idx);
        return random;
    }// end getPertanyaan_salapan

    //soal level 10
    Set<String> level10 = new HashSet<>(Arrays.asList(
            "budak leutik bisa ngapung",
            "Hirup kudu silih asih",
            "Moal ngejat sanajan ukur satapak",
            "Numpes musuh sarakah",
            "Larak lirik ningali ka bumi",
            "Mungguh nu hirup di dunya",
            "Henteu puguh nu dijugjug",
            "Naha naon dosa kula",
            "Nyiar bogo meunang kadal",
            "Di wétan pajar balébat")
    );
    List<String> list_level10 = new ArrayList<>(level10);

    //membuat getter untuk mengambil jawaban benar
    public String getPertanyaan_sapuluh(int idx) {
        int size = list_level10.size();
        for (int i = size; i > 1; i--) {
            idx = rand.shuffleList(i);
            Collections.swap(list_level10, i - 1, idx);
        }
        String random = list_level10.get(idx);
        return random;
    }// end getPertanyaan_sapuluh

}//end class




