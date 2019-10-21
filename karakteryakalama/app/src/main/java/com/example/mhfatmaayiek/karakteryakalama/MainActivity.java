package com.example.mhfatmaayiek.karakteryakalama;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textKalan ;
    TextView textSkor;
    int score;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;  //Runnable yi kullanmak için geçerli...
    Runnable runnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textKalan=findViewById(R.id.textKalan);
        textSkor=findViewById(R.id.textSkor);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageArray =new ImageView []{imageView1, imageView2, imageView3, imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        imageSakla();
        score=0;

        //Uygulama açıldığı gibi geriye saymasını istediğimiz için
        new CountDownTimer(20000,1000){

            @Override
            //her saniyede ne yapalım..
            public void onTick(long millisUntilFinished) {
                textKalan.setText("Kalan Süre: "+millisUntilFinished/1000);
            }

            @Override
            //Bitince ne olacak..
            public void onFinish() {
                //Bitince ne olacağını buraya yazıyoruz..
                 textKalan.setText("Süre Bitti");
                 handler.removeCallbacks(runnable); //süre bittiğinde runnable da durdurmak için
                 for(ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE); //bütün image ları görünmez
                 }

                 AlertDialog.Builder mesaj = new AlertDialog.Builder(MainActivity.this);
                 mesaj.setTitle("Skorunuz "+score);
                 mesaj.setMessage("Tekrar oynamak ister misin?");
                 mesaj.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         //
                         //                         //restart

                         Intent intent=getIntent();
                         //aynı activiteyi bitirip baştan açılmasını sağlıyor..

                         finish();
                         startActivity(intent);

                     }
                 });

                 mesaj.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         Toast.makeText(MainActivity.this,"Oyun bitti" ,Toast.LENGTH_LONG).show();



                     }
                 });

                 mesaj.show();
            }
        }.start();//CountDownTimer i başlat manasında..
    }

    public void skorNormal(View view){
        score+=10;
        textSkor.setText("Score: "+ score);

    }

    public void skorOrta(View view){
        score+=15;
        textSkor.setText("Score: "+ score);

    }

    public void skorZor(View view){
        score-=20;
        textSkor.setText("Score: "+score);


    }

    public void skorEnzor(View view){
        score+=50;
        textSkor.setText("Score: "+score);

    }

    public void imageSakla(){
        handler =new Handler();

        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE); //bütün image ları görünmez
                }
                Random random =new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);


                handler.postDelayed(this,1000);


            }
        };
        handler.post(runnable);


    }

}
