package com.example.instagramklonparse;


import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

//bu sınıfı kullanma amacımız bir array adapter
//FeedActivity de listview ile bağlayabileceğimiz arrayadapter olması..
public class PostClass extends ArrayAdapter<String> {
    private final ArrayList<String> kullaniciAdi;
    private final ArrayList<String> yorumlar;
    private final ArrayList<Bitmap> resimler;
    private final Activity context;

    public PostClass(ArrayList<String>kullaniciAdi, ArrayList<String> yorumlar, ArrayList<Bitmap> resimler,Activity context){

        //custom_view tanımladı..
        super(context,R.layout.custom_view,kullaniciAdi );
        this.kullaniciAdi=kullaniciAdi;
        this.yorumlar=yorumlar;
        this.resimler=resimler;
        this.context=context;
    }


    //getView yapmamızın amacı
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        //customview objeleştirdik..

        View customView=layoutInflater.inflate(R.layout.custom_view,null,true);
        TextView kullaniciAdiText=customView.findViewById(R.id.textView_custom);
        TextView yorumlarText=customView.findViewById(R.id.textView_command);
        ImageView resimlerText=customView.findViewById(R.id.imageView_customView);

        kullaniciAdiText.setText(kullaniciAdi.get(position));
        yorumlarText.setText(yorumlar.get(position));
        resimlerText.setImageBitmap(resimler.get(position));



        return customView;
    }
}
