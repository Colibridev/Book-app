package com.devcolibri.booksapp.di;

import com.devcolibri.booksapp.MainActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class)
public interface BookListComponent {
    void inject(MainActivity mainActivity);
}
