package com.example.androiddosyasistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalStorageGoruntule extends AppCompatActivity {

    TextView textViewYaz;
    EditText dosyaAdiTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage_goruntule);

        textViewYaz=findViewById(R.id.textViewYaz);
        dosyaAdiTxt=findViewById(R.id.dosyaAdiTxt);

    }
    public void getir(View view ){
        String dosyaAdi=dosyaAdiTxt.getText().toString();
        StringBuffer buffer=new StringBuffer();
        FileInputStream fis=null;
        try {
            fis=openFileInput(dosyaAdi);
            int read;
            // -1 dönerse artık dosyanın sonuna gelmiştir demek olduğundan döngü böyle sağlanıyor..
            while ((read=(fis.read()))!= -1)
            {
                //int ten char a dönüştürüp buffer ın içinde saklanacak.
                buffer.append((char) read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // Açılmış dosya varsa kapat
            if(fis!= null)
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        textViewYaz.setText(buffer);
    }
}
