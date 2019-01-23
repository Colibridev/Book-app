package com.devcolibri.booksapp.di;

import android.content.Context;

public class Injector {
    private static AppComponent appComponent;
    private static BookListComponent bookListComponent;
    private static BookDetailsComponent bookDetailsComponent;

    public static void init(Context applicationContext) {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(applicationContext))
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
