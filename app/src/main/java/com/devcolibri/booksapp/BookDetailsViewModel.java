package com.devcolibri.booksapp;

import javax.inject.Inject;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.devcolibri.booksapp.di.ActivityScope;

@ActivityScope
public class BookDetailsViewModel extends ViewModel {
    private MutableLiveData<Book> bookLiveData;
    private BookRepository bookRepository;
    private long bookId;

    @Inject
    public BookDetailsViewModel(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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

    @SuppressLint("StaticFieldLeak")
    private void loadBook() {
        new AsyncTask<Void, Void, Book>() {
            @Override
            protected Book doInBackground(Void... voids) {
                return bookRepository.getBook(bookId);
            }

            @Override
            protected void onPostExecute(Book book) {
                bookLiveData.setValue(book);
            }

        }.execute();
    }
}
