package com.devcolibri.booksapp.di;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.devcolibri.booksapp.AppDatabase;
import com.devcolibri.booksapp.BookDao;
import com.devcolibri.booksapp.BookService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private Context applicationContext;

    public AppModule(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Provides
    @Singleton
    AppDatabase providesAppDatabase() {
        return Room.databaseBuilder(applicationContext, AppDatabase.class, "book-database").build();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BookService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    BookService providesBookService(Retrofit retrofit) {
        return retrofit.create(BookService.class);
    }

    @Provides
    @Singleton
    BookDao providesBookDao(AppDatabase database) {
        return database.getBookDao();
    }

    @Provides
    @Singleton
    Executor providesExecutor() {
        int numberOfCores = Runtime.getRuntime().availableProcessors();
        return Executors.newFixedThreadPool(numberOfCores);
    }
}
