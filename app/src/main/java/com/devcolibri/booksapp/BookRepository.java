package com.devcolibri.booksapp;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BookRepository {
    private HttpClient httpClient;
    private BookDao bookDao;

    @Inject
    public BookRepository(HttpClient httpClient, BookDao bookDao) {
        this.httpClient = httpClient;
        this.bookDao = bookDao;
    }

    public List<Book> getBooks() {
        try {
            List<Book> books = httpClient.getBooks();
            bookDao.insertBooks(books);
            return bookDao.getBooks();
        } catch (IOException e) {
            return bookDao.getBooks();
        }
    }

    public Book getBook(long bookId) {
        return bookDao.getBook(bookId);
    }
}
