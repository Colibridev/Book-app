package com.devcolibri.booksapp.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.devcolibri.booksapp.AppDatabase;
import com.devcolibri.booksapp.BookDao;
import com.devcolibri.booksapp.BookDetailsViewModel;
import com.devcolibri.booksapp.BookListViewModel;
import com.devcolibri.booksapp.BookRepository;
import com.devcolibri.booksapp.BookService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class BookModule {

    private Context applicationContext;

    public BookModule(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Provides
    BookRepository providesBookRepository(AppDatabase db, Retrofit retrofit) {
        BookService bookService = retrofit.create(BookService.class);
        BookDao bookDao = db.getBookDao();
        return new BookRepository(bookService, bookDao);
    }

    @Provides
    BookListViewModel providesBookListViewModel(BookRepository bookRepository) {
        return new BookListViewModel(bookRepository);
    }

    @Provides
    BookDetailsViewModel providesBookDetailsListViewModel(BookRepository bookRepository) {
        return new BookDetailsViewModel(bookRepository);
    }

    @Provides
    AppDatabase providesAppDatabase() {
        return Room.databaseBuilder(applicationContext, AppDatabase.class, "book-database").build();
    }

    @Provides
    Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BookService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
