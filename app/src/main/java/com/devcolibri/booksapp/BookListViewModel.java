package com.devcolibri.booksapp;

import java.util.List;

import javax.inject.Inject;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

public class BookListViewModel extends ViewModel {
    private MutableLiveData<List<Book>> booksLiveData;
    private BookRepository bookRepository;

    @Inject
    public BookListViewModel(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
        new AsyncTask<Void, Void, List<Book>>() {
            @Override
            protected List<Book> doInBackground(Void... voids) {
                return bookRepository.getBooks();
            }

            @Override
            protected void onPostExecute(List<Book> books) {
                booksLiveData.setValue(books);
            }

        }.execute();
    }

}
