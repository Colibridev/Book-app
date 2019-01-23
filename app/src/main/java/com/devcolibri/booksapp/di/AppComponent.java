package com.devcolibri.booksapp.di;

import javax.inject.Singleton;

import com.devcolibri.booksapp.BookDao;
import com.devcolibri.booksapp.BookRepository;
import com.devcolibri.booksapp.BookService;

import dagger.Component;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    BookService bookService();
    BookDao bookDao();
    BookRepository bookRepository();
}
