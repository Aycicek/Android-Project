package com.example.androiddosyasistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class InternalStorageDemoo extends AppCompatActivity {
    private EditText dosyaAdi,mesaj,silinecekDosyaTxt;
    private TextView dosyaYoluTV,dosyaAdiTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage_demoo);
        init();

    }


    public void init(){
        dosyaAdi=findViewById(R.id.doyaAdieditText);
        mesaj=findViewById(R.id.mesajEditText);
        silinecekDosyaTxt=findViewById(R.id.silDosyaEditText);
        dosyaYoluTV=findViewById(R.id.dosyaYoluTV);
        dosyaAdiTV=findViewById(R.id.dosyaAdiTV);

    }

    public void veriKaydet(View view) throws FileNotFoundException {

        String adiDosya=dosyaAdi.getText().toString();
        String data=mesaj.getText().toString();

        FileOutputStream fos=null;
        try {
            fos=openFileOutput(adiDosya,MODE_PRIVATE);
            fos.write(data.getBytes());

        }catch (FileNotFoundException e){
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void dosyaGoster(View view){
        Intent intent=new Intent(this,InternalStorageGoruntule.class);
        startActivity(intent);

    }

    public void  internalDosyaYolu(View view){
        //Bu dosyaları tam olarak nereye kaydettiğini görmek için
         String dosyaYolu= ""+getFilesDir();
         dosyaYoluTV.setText(dosyaYolu);

    }

    public void internalDosyaListesi(View view){
        //dosyaAdiTV
        //Birden fazla dosya olcağı için string dizisine atayalım..
        String[] dosyalariListele=fileList();

        //Tüm okunanları tek bir Stringe atadık
        StringBuilder stringBuilder=new StringBuilder();
        for(String dosya:dosyalariListele){
            stringBuilder.append(dosya).append("  ,");
        }
        dosyaAdiTV.setText(stringBuilder);
    }

    public void dosyaSil(View view){
        //silDosyaEditText

        String silinecekDosya=silinecekDosyaTxt.getText().toString();
        boolean silindi=deleteFile(silinecekDosya);
        if (silindi==true){
            Toast.makeText(this, "Dosya başarılı bir şekilde silindi", Toast.LENGTH_SHORT).show();

        }

    }

}












