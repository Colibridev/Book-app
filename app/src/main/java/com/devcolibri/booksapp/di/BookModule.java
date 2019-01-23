package com.devcolibri.booksapp.di;

import com.devcolibri.booksapp.BookRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class BookModule {

    @Provides
    BookRepository providesBookRepository() {
        return new BookRepository();
    }
}
