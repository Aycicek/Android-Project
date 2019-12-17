package com.example.gsonilesharedpreferences;

import java.util.List;

public class Ogrenci {

    private String adi;
    private String bolum;
    private Integer id;
    private List<String> gorev; //ödevler propjeler gibi
    private boolean aktif;

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getBolum() {
        return bolum;
    }

    public void setBolum(String bolum) {
        this.bolum = bolum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getGorev() {
        return gorev;
    }

    public void setGorev(List<String> gorev) {
        this.gorev = gorev;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

}






