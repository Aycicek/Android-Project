package com.example.gsonilesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textViewData);
    }

    public void kaydet(View view){
        
        //Bu butona tıklandığında nesne oluşturulacak ve ogrenciOlustur method çağrılacak..

        Ogrenci ogrenci = ogrenciOlustur();
        SharedPreferences sharedPreferences=getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        //put ile değerleri alamıyoruz çünkü primitive tipte değil bi sınıf içinde bunu için de GSON kullanılacak
        //Gsonu kullanabilmek için öncelikle dependencies etmemiz gerekiyor.
        //File>Project Scructure>Dependencies>+ işaretine basılıp GSON yazılır ve ilk çıkana tıklanıp ok diyilir ve gradle eyükleme gerçekleşir..
        Gson gson=new Gson();
        String jsonStre =gson.toJson(ogrenci,Ogrenci.class); //BÜTÜN VERİLERİ Json formatına çeviriyor string olarak
        Log.i("Fatma","Save"+jsonStre);//Kaydedilenleri loggat e bastırmak için yaptık..

        editor.putString("Ogrenci_anahtar",jsonStre);
        editor.apply();

    }

    public void getir(View view){

        SharedPreferences sharedPreferences =getPreferences(MODE_PRIVATE);
        String strString=sharedPreferences.getString("Ogrenci_anahtar","");
        Log.i("Fatma","Getirildi"+strString);
        Gson gson=new Gson();
        Ogrenci ogrenci=gson.fromJson(strString,Ogrenci.class);

        ogrenciGöster(ogrenci);
    }



    private void ogrenciGöster(Ogrenci ogrenci){
        String bilgiler="Adı:"+ogrenci.getAdi()
                + "\n" +"Bölümü:"+ ogrenci.getBolum()
                + "\n" +"Numarası:"+ ogrenci.getId()
                + "\n" +"Görevleri:" +ogrenci.getGorev();

        textView.setText(bilgiler);

    }


    private Ogrenci ogrenciOlustur(){


        Ogrenci ogrenci= new Ogrenci();
        ogrenci.setAdi("Fatma");
        ogrenci.setBolum("Yazılım Mühendisliği");
        ogrenci.setAktif(true);
        ogrenci.setId(150);
        //Arrays.asList() Bu kullanımda şunu söylemiş oluyoruz buraya yazılanları listeye ekle
        ogrenci.setGorev(Arrays.asList("Sınıf Temsilcisi","Developer"));
        return ogrenci;



    }
}
