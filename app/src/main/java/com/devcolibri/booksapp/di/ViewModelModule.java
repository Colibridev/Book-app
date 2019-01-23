package com.devcolibri.booksapp.di;

import android.arch.lifecycle.ViewModel;

import com.devcolibri.booksapp.BookDetailsViewModel;
import com.devcolibri.booksapp.BookListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(BookListViewModel.class)
    public abstract ViewModel bookListViewModel(BookListViewModel bookListViewModel);

    @IntoMap
    @Binds
    @ViewModelKey(BookDetailsViewModel.class)
    public abstract ViewModel bookDetailsViewModel(BookDetailsViewModel bookDetailsViewModel);
}