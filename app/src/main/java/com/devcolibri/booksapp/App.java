package com.devcolibri.booksapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static App instance;
    private static AppDatabase database;
    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static AppDatabase getDb() {
        if(database == null) {
            database = Room.databaseBuilder(instance, AppDatabase.class, "book-database").build();
        }
        return database;
    }

    public static Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BookService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}