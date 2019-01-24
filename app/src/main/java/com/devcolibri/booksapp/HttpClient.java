package com.devcolibri.booksapp;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HttpClient {
    private BookService bookService;

    @Inject
    public HttpClient(BookService bookService) {
        this.bookService = bookService;
    }

    public List<Book> getBooks() throws IOException {
        return bookService.getBooks().execute().body();
    }
}
