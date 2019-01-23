package com.devcolibri.booksapp.di;

import com.devcolibri.booksapp.MainActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent
public interface BookListComponent {
    void inject(MainActivity mainActivity);
}
