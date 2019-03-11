package com.rj.eventapp;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class EventApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
