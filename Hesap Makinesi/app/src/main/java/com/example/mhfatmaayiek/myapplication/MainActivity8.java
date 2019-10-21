package com.example.mhfatmaayiek.myapplication;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity8 extends AppCompatActivity {


    //tanımlama

    TextView textSonuc;
    EditText editText ;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {  //uygulama açıldığı gibi bir şey yapmasını istiyorsak oncreate metodu altına yazılır.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);


        //başlatma..

         editText =findViewById(R.id.editText);
         editText2 =findViewById(R.id.editText2);
         textSonuc =findViewById(R.id.textView);
    }


    public void toplaButton(View view){


        //matches ile boş olma durumu kontrol ediliyor
        // eğer edittext in içi boşşa alertdiyalog ile ekrana bu alanın boş geçilemeyeceği mesajı bildiriliyor..
        if (editText.getText().toString().matches("") || editText2.getText().toString().matches("")) {

            AlertDialog.Builder hatamesaji = new AlertDialog.Builder(MainActivity8.this);
            hatamesaji.setMessage("Alanlar boş geçilmez");
            hatamesaji.setPositiveButton("Anladım", null);
            hatamesaji.show();
        }

        // edittext içindeki sayıları almak için
        else {

            int a= Integer.parseInt(editText.getText().toString());
            int b= Integer.parseInt(editText2.getText().toString());
            int sonuc=a+b;

            textSonuc.setText("Sonuc " + sonuc); // İki nokta ile Sonuç yazmamızın nedeni setText içinde string bir şeyler araması..

        }


    }

    public void cikarButton(View view){

        if (editText.getText().toString().matches("") || editText2.getText().toString().matches("")) {

            AlertDialog.Builder hatamesaji = new AlertDialog.Builder(MainActivity8.this);
            hatamesaji.setMessage("Alanlar boş geçilmez");
            hatamesaji.setNegativeButton("Anladım", null);
            hatamesaji.show();
        }

        else {
            int a = Integer.parseInt(editText.getText().toString());
            int b = Integer.parseInt(editText2.getText().toString());
            int sonuc = a - b;

            textSonuc.setText("Sonuc " + sonuc);
        }

    }

    public void carpButton(View view){

        if (editText.getText().toString().matches("") || editText2.getText().toString().matches("")) {

            AlertDialog.Builder hatamesaji = new AlertDialog.Builder(MainActivity8.this);
            hatamesaji.setMessage("Alanlar boş geçilmez");
            hatamesaji.setNegativeButton("Anladım", null);
            hatamesaji.show();
        }

        else {
            int a = Integer.parseInt(editText.getText().toString());
            int b = Integer.parseInt(editText2.getText().toString());
            int sonuc = a * b;

            textSonuc.setText("Sonuc " + sonuc);
        }


    }


    public void bolButton(View view){
        if (editText.getText().toString().matches("") || editText2.getText().toString().matches("")) {

            AlertDialog.Builder hatamesaji = new AlertDialog.Builder(MainActivity8.this);
            hatamesaji.setMessage("Alanlar boş geçilmez");
            hatamesaji.setNegativeButton("Anladım", null);
            hatamesaji.show();
        }

        else {
            int a = Integer.parseInt(editText.getText().toString());
            int b = Integer.parseInt(editText2.getText().toString());
            int sonuc = a / b;

            textSonuc.setText("Sonuc " + sonuc);
        }


    }
}
