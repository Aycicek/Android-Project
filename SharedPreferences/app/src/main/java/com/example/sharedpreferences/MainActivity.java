package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText adi,meslegi;
    private TextView ad,meslek;
    private Button kaydet,getir;
    private Switch renkSwitch;
    private ConstraintLayout ana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        renkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //değişiklik durumlarının ele alındığı method
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               sayfaArkaPlanDegistir(isChecked);

            }
        });
    }

    private void sayfaArkaPlanDegistir(boolean isChecked) {
        SharedPreferences sharedPreferences=getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(Sabit.Anahtar_Renk,isChecked);// isCheck içinde ya true ya da false olacaktır..
        editor.apply();
        ana.setBackgroundColor(isChecked ? Color.GRAY : Color.WHITE);

    }

    public void init(){
        adi=findViewById(R.id.editTextAdi);
        meslegi=findViewById(R.id.editTextMeslegi);
        ad=findViewById(R.id.adTW);
        meslek=findViewById(R.id.meslekTW);
        kaydet=findViewById(R.id.kaydetButton);
        getir=findViewById(R.id.getirButton);
        renkSwitch=findViewById(R.id.switchRenk);
        ana=(ConstraintLayout) findViewById(R.id.anaLayout);
    }

    public void kaydet(View view){

        //Bu butona tıklandığında SharedPreferences dosyasına verileri kaydedeceğiz..
        //getPreferences() bu sadece activity de çalışır(Bulunduğu aktivityde)
        SharedPreferences sharedPreferences=getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Sabit.Anahtar_ISIM,adi.getText().toString());
        editor.putString(Sabit.Anahtar_Meslek,meslegi.getText().toString());

        //Yapılan değişiklikleri kaydetmek için editor.commit() de kullanılabilir aralarında küçük farklar vardır..
        editor.apply();
        Toast.makeText(this, "Veriler Kaydedildi..", Toast.LENGTH_SHORT).show();



    }

    public void getir(View view){

        SharedPreferences sharedPreferences=getPreferences(Context.MODE_PRIVATE);

        //Verileri okumak için bu metodu kullanıyoruz..
        String isim=sharedPreferences.getString(Sabit.Anahtar_ISIM,"Yok");
        String meslekk=sharedPreferences.getString(Sabit.Anahtar_Meslek,"Yok");

        ad.setText(isim);
        meslek.setText(meslekk);

    }
}
