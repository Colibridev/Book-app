package com.devcolibri.booksapp;

import java.io.IOException;
import java.util.List;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BooksRecyclerAdapter booksAdapter;
    private BookService bookService;
    private BookDao bookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        booksAdapter = new BooksRecyclerAdapter((v) -> {
            // TODO добавить переход на следующую Activity
        });
        recyclerView.setAdapter(booksAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BookService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bookService = retrofit.create(BookService.class);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "book-database").build();
        bookDao = db.getBookDao();

        loadBooks();
    }

    @SuppressLint("StaticFieldLeak")
    private void loadBooks() {
        new AsyncTask<Void, Void, List<Book>>() {
            @Override
            protected List<Book> doInBackground(Void... voids) {
                try {
                    List<Book> books = bookService.getBooks().execute().body();
                    bookDao.insertBooks(books);
                    return bookDao.getBooks();
                } catch (IOException e) {
                    return bookDao.getBooks();
                }
            }

            @Override
            protected void onPostExecute(List<Book> books) {
                if(books != null) {
                    booksAdapter.setItems(books);
                } else {
                    Toast.makeText(MainActivity.this, "Произошла ошибка", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
