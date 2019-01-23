package com.devcolibri.booksapp.di;

import javax.inject.Singleton;

import com.devcolibri.booksapp.DetailsActivity;
import com.devcolibri.booksapp.MainActivity;

import dagger.Component;

@Component(modules = {AppModule.class, ViewModelModule.class})
@Singleton
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(DetailsActivity detailsActivity);
}
