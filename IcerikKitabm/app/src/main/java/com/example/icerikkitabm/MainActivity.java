package com.example.icerikkitabm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView =findViewById(R.id.listView);

        Animasyonlar akademisyen=new Animasyonlar("Akademisyen",R.drawable.akademisyen);
        Animasyonlar astronot=new Animasyonlar("Astronot",R.drawable.astronot);
        Animasyonlar disci=new Animasyonlar("Diş Hekimi",R.drawable.disci);
        Animasyonlar doktor=new Animasyonlar("Doktor",R.drawable.doktor);
        Animasyonlar hakim=new Animasyonlar("Hakim",R.drawable.hakim);
        Animasyonlar itfaiye=new Animasyonlar("İtfaiyeci",R.drawable.itfaiye);
        Animasyonlar kaptan=new Animasyonlar("Gemi Kaptanı",R.drawable.kaptan);
        Animasyonlar mimar=new Animasyonlar("Mimar",R.drawable.mimar);
        Animasyonlar muhendis=new Animasyonlar("Mühendis",R.drawable.muh);
        Animasyonlar muzisyen=new Animasyonlar("Müzisyen",R.drawable.muzisyen);
        Animasyonlar ogretmen=new Animasyonlar("Öğretmen",R.drawable.ogretmen);
        Animasyonlar pilot =new Animasyonlar("Pilot",R.drawable.pilot);
        Animasyonlar polis=new Animasyonlar("Polis",R.drawable.polis);
        Animasyonlar psikolog=new Animasyonlar("Psikolog",R.drawable.psikopat);
        Animasyonlar sarkici=new Animasyonlar("Şarkıcı",R.drawable.sarkici);
        Animasyonlar ressam=new Animasyonlar("Ressam",R.drawable.ressam);


        final ArrayList<Animasyonlar> animasyonlarArrayList=new ArrayList<>();
        animasyonlarArrayList.add(akademisyen);
        animasyonlarArrayList.add(astronot);
        animasyonlarArrayList.add(disci);
        animasyonlarArrayList.add(doktor);
        animasyonlarArrayList.add(hakim);
        animasyonlarArrayList.add(itfaiye);
        animasyonlarArrayList.add(kaptan);
        animasyonlarArrayList.add(mimar);
        animasyonlarArrayList.add(muhendis);
        animasyonlarArrayList.add(muzisyen);
        animasyonlarArrayList.add(ogretmen);
        animasyonlarArrayList.add(pilot);
        animasyonlarArrayList.add(polis);
        animasyonlarArrayList.add(psikolog);
        animasyonlarArrayList.add(sarkici);
        animasyonlarArrayList.add(ressam);



        CustomAdapter customAdapter=new CustomAdapter(animasyonlarArrayList,MainActivity.this);
        listView.setAdapter(customAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,detaylar.class);

                intent.putExtra("seçilenMeslek",animasyonlarArrayList.get(position));
                startActivity(intent);

            }
        });

    }
}
