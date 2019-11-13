package com.example.instagramklonparse;

import android.app.Application;

import com.parse.Parse;

//bu sınıfı manifestte tanımlamamız gerekiyor..
public class ParseStarter extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
        Parse.initialize(new Parse.Configuration.Builder(this)
        .applicationId("DpQpMMkv8g4pMvWdRLVFW8r1kqLTkP2ilM7YVLqr")
        .clientKey("hGSn6RnE21yHFzeSx87kqPVn1OwfP5SecEN4LChx")
        .server("https://parseapi.back4app.com/")
        .build()
        );

    }
}
