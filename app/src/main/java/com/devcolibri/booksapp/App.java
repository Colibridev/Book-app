package com.devcolibri.booksapp;

import android.app.Application;

import com.devcolibri.booksapp.di.AppComponent;
import com.devcolibri.booksapp.di.AppModule;
import com.devcolibri.booksapp.di.BookDetailsComponent;
import com.devcolibri.booksapp.di.BookListComponent;
import com.devcolibri.booksapp.di.DaggerAppComponent;

public class App extends Application {

    private static App instance;
    private static AppComponent appComponent;
    private static BookListComponent bookListComponent;
    private static BookDetailsComponent bookDetailsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static BookDetailsComponent getBookDetailsComponent() {
        if (bookDetailsComponent == null) {
            bookDetailsComponent = appComponent.plusBookDetailsComponent();
        }
        return bookDetailsComponent;
    }

    public static void destroyBookDetailsComponent() {
        bookDetailsComponent = null;
    }


    public static BookListComponent getBookListComponent() {
        if (bookListComponent == null) {
            bookListComponent = appComponent.plusBookListComponent();
        }

        return bookListComponent;
    }

    public static void destroyBookListComponent() {
        bookListComponent = null;
    }

}