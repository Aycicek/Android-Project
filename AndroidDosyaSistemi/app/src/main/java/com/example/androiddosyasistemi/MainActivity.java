package com.example.androiddosyasistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void internalButtonGit(View view ){
        Intent intent =new Intent(this,InternalStorageDemoo.class);
        startActivity(intent);

    }

    public void internalCacheButtonGit(View view){
        Intent intent =new Intent(this,Cache_Storage_Demo.class);
        startActivity(intent);
    }
}
