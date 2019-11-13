package com.example.instagramklonparse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {
    ListView listView;

    ArrayList<String> kullaniciAdiList;
    ArrayList<String> yorumlarList;
    ArrayList<Bitmap> resimlerList;
    PostClass postClass;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.ana_ekran,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.add_post){
            Intent intent =new Intent(getApplicationContext(),UploadAvtivity.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.logout){
            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override

                public void done(ParseException e) {
                    if(e != null){
                        Toast.makeText(FeedActivity.this, "Çıkış Yapılamadı", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        Toast.makeText(FeedActivity.this, "Çıkış Yapıldı " + MainActivity.globaluser, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class) ;
                        startActivity(intent);

                    }
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        //bu listview de olan görüntüleri ayrı bi xml de yapmamız gerekiyor.
        listView=findViewById(R.id.listView);

        kullaniciAdiList=new ArrayList<>();
        yorumlarList=new ArrayList<>();
        resimlerList=new ArrayList<>();

        postClass=new PostClass(kullaniciAdiList,yorumlarList,resimlerList,this);
        listView.setAdapter(postClass);

        download();

    }

    public void download() {
        ParseQuery<ParseObject> query=ParseQuery.getQuery("Posts");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null) {
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    if(objects.size()>0){
                        for(final ParseObject object: objects){
                            ParseFile parseFile=(ParseFile) object.get("resim");
                            parseFile.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if(e==null && data!=null){
                                        Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);

                                        resimlerList.add(bitmap);
                                        kullaniciAdiList.add(object.getString("username"));
                                        yorumlarList.add(object.getString("yorum"));
                                        postClass.notifyDataSetChanged();

                                    }

                                }
                            });
                        }
                    }

                }
            }
        });

    }
}
