package com.devcolibri.booksapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.devcolibri.booksapp.di.DaggerBookComponent;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BooksRecyclerAdapter booksAdapter;

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

        BookListViewModel viewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        DaggerBookComponent.create().inject(viewModel);
        viewModel.getBooks().observe(this, books -> {
            booksAdapter.setItems(books);
        });
    }
}
