package com.devcolibri.booksapp.di;

import com.devcolibri.booksapp.DetailsActivity;
import com.devcolibri.booksapp.MainActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class)
public interface BookComponent {
    void inject(MainActivity mainActivity);
    void inject(DetailsActivity detailsActivity);
}
