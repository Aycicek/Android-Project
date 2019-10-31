package com.example.icerikkitabm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class detaylar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaylar);

        ImageView imageView=findViewById(R.id.imageView);
        TextView textView =findViewById(R.id.textViewmeslek);

        Intent intent=getIntent();
        Animasyonlar secilenMeslek=(Animasyonlar) intent.getSerializableExtra("seçilenMeslek");

        Bitmap bitmap= BitmapFactory.decodeResource(getApplicationContext().getResources(),secilenMeslek.getResimInteger());
        imageView.setImageBitmap(bitmap);

        textView.setText(secilenMeslek.getMeslek());

    }
}
