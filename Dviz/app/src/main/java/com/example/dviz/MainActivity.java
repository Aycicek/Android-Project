package com.example.dviz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView tryTextView;
    TextView usdTextView;
    TextView cadTextView;
    TextView jpyTextView;
    TextView chfTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tryTextView=findViewById(R.id.tryTextView);
        usdTextView=findViewById(R.id.usdTextView);
        cadTextView=findViewById(R.id.cadTextView);
        jpyTextView=findViewById(R.id.jpyTextView);
        chfTextView=findViewById(R.id.chfTextView);
    }

    public void getRates(View view){

        DownloadData downloadData=new DownloadData();
        try {

            String url="http://data.fixer.io/api/latest?access_key=18a230ef4c40bb22858c767e97cf96e6";
            downloadData.execute(url);
        }catch (Exception e){

        }
    }

    private class DownloadData extends AsyncTask<String, Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            String result="";
            URL url;
            HttpURLConnection httpURLConnection;

            try {

                url=new URL(strings[0]);
                httpURLConnection=(HttpURLConnection) url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);

                int data= inputStreamReader.read();

                while(data>0){
                    char character =(char) data;
                    result += character;

                    data=inputStreamReader.read();

                }
                return result;

            }
            catch (Exception e){
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //bütün işlemler bitiikten sonra ne yapacağı..
            //System.out.println("Alınan data:"+ s);

            try {
                //bunu yapmamızın amacı olmayan bi veriyi çekmeye çalışırsak programımız çökmesin diye
                //json olarak almamızn amacı bunu kullanarak sadece istediğimizi yazarak ona ulaşabiliriz.

                JSONObject jsonObject=new JSONObject(s);
                String base=jsonObject.getString("base");
                String rates=jsonObject.getString("rates");

                JSONObject jsonObject1=new JSONObject(rates);
                String turkLira=jsonObject1.getString("TRY");
                tryTextView.setText("TRY: "+turkLira);

                String usdLira=jsonObject1.getString("USD");
                usdTextView.setText("USD: "+usdLira);

                String cadLira=jsonObject1.getString("CAD");
                cadTextView.setText("CAD: "+cadLira);

                String jpyLira=jsonObject1.getString("JPY");
                jpyTextView.setText("JPY: "+jpyLira);

                String chfLira=jsonObject1.getString("CHF");
                chfTextView.setText("CHF: "+chfLira);


            }catch (Exception e){

            }
        }


    }
}
