package com.devcolibri.booksapp;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BookRepositoryTest {
    private static final List<Book> BOOKS_RESPONSE = Arrays.asList(
            new Book(1L, "imageUrl", "title", "description")
    );
    private static final Book BOOK_RESPONSE = new Book(2L, "imageUrl1", "title1", "description1");


    @Mock
    private BookDao bookDao;
    @Mock
    private HttpClient httpClient;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getBooks_validApiResponse_insertedToDbAndReturnDbAnswer() throws IOException {
        // arrange
        Mockito.when(httpClient.getBooks()).thenReturn(BOOKS_RESPONSE);
        Mockito.when(bookDao.getBooks()).thenReturn(BOOKS_RESPONSE);
        BookRepository repository = new BookRepository(httpClient, bookDao);

        // act
        List<Book> result = repository.getBooks();

        // assert
        Assert.assertEquals(BOOKS_RESPONSE, result);
        Mockito.verify(bookDao).insertBooks(BOOKS_RESPONSE);

    }

    @Test
    public void getBooks_errorApiResponse_ReturnDbAnswer() throws IOException {
        // arrange
        Mockito.when(httpClient.getBooks()).thenThrow(new IOException());
        Mockito.when(bookDao.getBooks()).thenReturn(BOOKS_RESPONSE);
        BookRepository repository = new BookRepository(httpClient, bookDao);

        // act
        List<Book> result = repository.getBooks();

        // assert
        Assert.assertEquals(BOOKS_RESPONSE, result);
        Mockito.verify(bookDao, Mockito.never()).insertBooks(BOOKS_RESPONSE);

    }

    @Test
    public void getBook_validDbResponse_returnDbAnswer() {
        // arrange
        long bookId = 1L;
        Mockito.when(bookDao.getBook(bookId)).thenReturn(BOOK_RESPONSE);
        BookRepository repository = new BookRepository(httpClient, bookDao);

        // act
        Book result = repository.getBook(bookId);

        // assert
        Assert.assertEquals(BOOK_RESPONSE, result);
    }
}