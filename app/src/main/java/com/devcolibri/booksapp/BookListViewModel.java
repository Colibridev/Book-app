package com.devcolibri.booksapp;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class BookListViewModel extends ViewModel {
    private MutableLiveData<List<Book>> booksLiveData;
    private BookRepository bookRepository;
    private Executor executor;

    @Inject
    public BookListViewModel(BookRepository bookRepository, Executor executor) {
        this.bookRepository = bookRepository;
        this.executor = executor;
    }


    public LiveData<List<Book>> getBooks() {
        if(booksLiveData == null) {
            booksLiveData = new MutableLiveData<>();
            loadBooks();
        }
        return booksLiveData;
    }

    @SuppressLint("StaticFieldLeak")
    private void loadBooks() {
        executor.execute(() -> {

            List<Book> books = bookRepository.getBooks();
            booksLiveData.postValue(books);

        });
    }

}
