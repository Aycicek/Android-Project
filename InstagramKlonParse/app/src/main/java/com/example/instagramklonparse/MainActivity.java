package com.example.instagramklonparse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {
    EditText editText, editText1;
    Button button,button1;
    static String globaluser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.nameeditText);
        editText1=findViewById(R.id.sifreeditText2);

        //altta getCurrentUser() kullanmamızın amacı güncel kullanıcıyı al yani daha önce giriş yapmış kulllanıcı varsa onu kullan..
        ParseUser parseUser=ParseUser.getCurrentUser();

        if(parseUser!= null){
            Intent intent =new Intent(getApplicationContext(), FeedActivity.class);
            startActivity(intent);
        }


    }

    public void singIn(View view){
        ParseUser .logInInBackground(editText.getText().toString(), editText1.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e!=null){
                    Toast.makeText(MainActivity.this, "Yanlış girdiniz lütfen kontrol ediniz", Toast.LENGTH_SHORT).show();
                }else{
                    globaluser = user.getUsername();
                    //intent
                    Toast.makeText(MainActivity.this, "Hoşgeldin "+globaluser, Toast.LENGTH_SHORT).show();

                    Intent intent =new Intent(getApplicationContext(), FeedActivity.class);
                    startActivity(intent);

                }
            }
        });
        

    }

    public void singUp(View view){

        //kullanıcıyı kaydetmek için..

        ParseUser user=new ParseUser();
        user.setUsername(editText.getText().toString());
        user.setPassword(editText1.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    Toast.makeText(MainActivity.this, "Hata bulundu", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(MainActivity.this, "Kullanıcı Oluşturuldu", Toast.LENGTH_SHORT).show();
                    //intent

                }
            }
        });


    }
}
