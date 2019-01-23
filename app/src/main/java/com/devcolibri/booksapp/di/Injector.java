package com.devcolibri.booksapp.di;

import android.content.Context;

public class Injector {
    private static AppComponent appComponent;

    public static void init(Context applicationContext) {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(applicationContext))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
