package com.devcolibri.booksapp;

import javax.inject.Inject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.devcolibri.booksapp.di.AppComponent;
import com.devcolibri.booksapp.di.AppModule;
import com.devcolibri.booksapp.di.DaggerAppComponent;
import com.devcolibri.booksapp.di.DaggerBookListComponent;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BooksRecyclerAdapter booksAdapter;
    @Inject BookListViewModel viewModel;

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

        AppComponent appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();

        DaggerBookListComponent
                .builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        
        viewModel.getBooks().observe(this, books -> {
            booksAdapter.setItems(books);
        });
    }
}
