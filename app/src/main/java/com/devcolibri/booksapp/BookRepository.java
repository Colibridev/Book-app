package com.devcolibri.booksapp;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BookRepository {
    private BookService bookService;
    private BookDao bookDao;

    @Inject
    public BookRepository(BookService bookService, BookDao bookDao) {
        this.bookService = bookService;
        this.bookDao = bookDao;
    }

    public List<Book> getBooks() {
        try {
            List<Book> books = bookService.getBooks().execute().body();
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
