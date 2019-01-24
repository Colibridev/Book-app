package com.devcolibri.booksapp;

import java.util.concurrent.Executor;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BookDetailsViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    public Executor TEST_EXECUTOR = command -> command.run();

    private static final Book BOOK_RESPONSE = new Book(1L, "imageUrl", "title", "description");

    @Mock
    private BookRepository repository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getBooks_validResponse_returnRepositoryAnswer() {
        // arrange
        long bookId = 1L;
        Mockito.when(repository.getBook(bookId)).thenReturn(BOOK_RESPONSE);
        BookDetailsViewModel viewModel = new BookDetailsViewModel(repository, TEST_EXECUTOR);
        viewModel.init(bookId);

        // act
        Book result = viewModel.getBook().getValue();

        // assert
        Assert.assertEquals(BOOK_RESPONSE, result);
    }

}
