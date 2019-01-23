package com.devcolibri.booksapp;

import android.app.Application;

import com.devcolibri.booksapp.di.Injector;

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Injector.init(this);
    }

}