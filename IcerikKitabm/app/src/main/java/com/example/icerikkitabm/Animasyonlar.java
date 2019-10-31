package com.example.icerikkitabm;

import java.io.Serializable;

public class Animasyonlar implements Serializable {

    private String meslek;
    private int resimInteger;

    public Animasyonlar(String meslek,int resimInteger){
        this.meslek=meslek;
        this.resimInteger=resimInteger;
    }

    public String getMeslek() {
        return meslek;
    }

    public int getResimInteger() {
        return resimInteger;
    }
}
