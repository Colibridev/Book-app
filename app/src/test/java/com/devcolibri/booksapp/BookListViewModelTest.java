package com.devcolibri.booksapp;

import java.util.Arrays;
import java.util.List;
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

public class BookListViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    public Executor TEST_EXECUTOR = command -> command.run();

    private static final List<Book> BOOKS_RESPONSE = Arrays.asList(
        new Book(1L, "imageUrl", "title", "description")
    );

    @Mock
    private BookRepository repository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getBooks_validResponse_returnRepositoryAnswer() {
        // arrange
        Mockito.when(repository.getBooks()).thenReturn(BOOKS_RESPONSE);
        BookListViewModel viewModel = new BookListViewModel(repository, TEST_EXECUTOR);

        // act
        List<Book> result = viewModel.getBooks().getValue();

        // assert
        Assert.assertEquals(BOOKS_RESPONSE, result);
    }

}