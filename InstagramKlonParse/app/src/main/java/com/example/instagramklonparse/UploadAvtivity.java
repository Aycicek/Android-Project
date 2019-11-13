package com.example.instagramklonparse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UploadAvtivity extends AppCompatActivity {

    ImageView imageViewResim;
    EditText textViewYorum;
    Button buttonPaylas;
    Bitmap secilenResim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_avtivity);
        imageViewResim=findViewById(R.id.imageViewResim);
        textViewYorum=findViewById(R.id.textViewYorum);
        buttonPaylas=findViewById(R.id.paylasButton);
    }

    public void upload(View view){

        //resim dosyasını upload etme
        String yorumText=textViewYorum.getText().toString();

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        secilenResim.compress(Bitmap.CompressFormat.PNG, 50,byteArrayOutputStream);
        byte[] bytes=byteArrayOutputStream.toByteArray();

        ParseFile parseFile=new ParseFile("resim.png",bytes);

        ParseObject parseObject=new ParseObject("Posts");
        parseObject.put("resim", parseFile);
        parseObject.put("yorum", yorumText);
        parseObject.put("username", ParseUser.getCurrentUser().getUsername());
        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Post Upload",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),FeedActivity.class);
                    startActivity(intent);

                }

            }
        });



    }

    public void resimsec(View view){
        //kullanıcının galerisine gidilecek
        //ve galeriye gidebilmek için çeşitli izinler almamız gerekiyor

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},2);

        }else{
            Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            //seçilen resmin nerde durduğunu aldık
            startActivityForResult(intent,1);
        }

    }

    //izin verilince ne olacak..


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //izin verilmiş mi
        //Verilmişse
        if(requestCode==2){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //başlatılınca yapılacak olan
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==1 && resultCode==RESULT_OK && data != null){
            Uri uri =data.getData();

            try {
                secilenResim=MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                imageViewResim.setImageBitmap(secilenResim);
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
