package com.rj.eventapp.app;

import android.app.Application;

import com.google.firebase.FirebaseApp;

//RAVIRAJ

public class EventApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
