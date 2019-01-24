package com.devcolibri.booksapp;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class BookDetailsViewModel extends ViewModel {
    private BookRepository bookRepository;
    private Executor executor;

    private MutableLiveData<Book> bookLiveData;
    private long bookId;

    @Inject
    public BookDetailsViewModel(BookRepository bookRepository, Executor executor) {
        this.bookRepository = bookRepository;
        this.executor = executor;
    }


    public void init(long bookId) {
        this.bookId = bookId;
    }

    public LiveData<Book> getBook() {
        if(bookLiveData == null) {
            bookLiveData = new MutableLiveData<>();
            loadBook();
        }
        return bookLiveData;
    }

    private void loadBook() {
        executor.execute(() -> {

            Book book = bookRepository.getBook(bookId);
            bookLiveData.postValue(book);

        });
    }
}
