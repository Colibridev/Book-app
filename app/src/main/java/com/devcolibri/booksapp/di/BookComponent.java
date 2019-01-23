package com.devcolibri.booksapp.di;

import com.devcolibri.booksapp.BookListViewModel;

import dagger.Component;

@Component(modules = BookModule.class)
public interface BookComponent {
    void inject(BookListViewModel bookListViewModel);
}
