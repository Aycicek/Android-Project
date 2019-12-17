package com.example.activityfragmentveriaktarimi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText sayi1,sayi2;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sayi1=findViewById(R.id.sayi1);
        sayi2=findViewById(R.id.sayi2);
        //manager.beginTransaction();
    }

    public void gonder(View view){
        Toast.makeText(this, "22222", Toast.LENGTH_SHORT).show();
        /*BlankFragment hesapla=new BlankFragment();
        Bundle bundle=new Bundle();*/
       /* int birinci=Integer.parseInt(sayi1.getText().toString());
        int ikinci=Integer.parseInt(sayi2.getText().toString());
        /*bundle.putInt("sayi1",birinci);
        bundle.putInt("sayi2",ikinci);

       /* FragmentTransaction ft=manager.beginTransaction();
        ft.replace(R.id.main,hesapla,"Hesapla");
        ft.commit();*/
    }
}
