package com.devcolibri.booksapp;

import javax.inject.Inject;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.devcolibri.booksapp.di.Injector;
import com.devcolibri.booksapp.di.ViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BooksRecyclerAdapter booksAdapter;
    @Inject ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        booksAdapter = new BooksRecyclerAdapter((book) -> {

            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(DetailsActivity.BOOK_ID_EXTRA, book.getId());
            startActivity(intent);

        });
        recyclerView.setAdapter(booksAdapter);

        Injector.getAppComponent().inject(this);

        BookListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(BookListViewModel.class);
        viewModel.getBooks().observe(this, books -> {
            booksAdapter.setItems(books);
        });
    }
}
