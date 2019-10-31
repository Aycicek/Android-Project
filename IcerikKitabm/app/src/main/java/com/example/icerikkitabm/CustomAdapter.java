package com.example.icerikkitabm;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Animasyonlar> {

    private ArrayList<Animasyonlar> animasyon;
    private Activity context;

    public CustomAdapter(ArrayList<Animasyonlar> animasyon,Activity context){
        super(context,R.layout.custom_view,animasyon);
        this.animasyon=animasyon;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Listede her bir elemanın pozisyonu var...

        LayoutInflater layoutInflater= context.getLayoutInflater();
        View customView =layoutInflater.inflate(R.layout.custom_view,null,true);
        //Yukarıda tanımladığımız View sayesinde custom_view i bir değişken gibi kullanabileceğiz..

        TextView meslek=customView.findViewById(R.id.textViewCustom);
        meslek.setText(animasyon.get(position).getMeslek());


        return customView;
    }
}
