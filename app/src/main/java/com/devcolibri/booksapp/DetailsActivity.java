package com.devcolibri.booksapp;

import javax.inject.Inject;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.devcolibri.booksapp.di.Injector;
import com.devcolibri.booksapp.di.ViewModelFactory;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    private TextView titleTextView;
    private TextView descriptionTextView;
    private ImageView imageView;
    public static String BOOK_ID_EXTRA = "bookId";

    @Inject ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        titleTextView = findViewById(R.id.title_text_view);
        descriptionTextView = findViewById(R.id.description_text_view);
        imageView = findViewById(R.id.image_view);

        long bookId = getIntent().getLongExtra(BOOK_ID_EXTRA, -1);
        if (bookId == -1) throw new IllegalArgumentException("Необходимо передать bookId параметр");

        Injector.getAppComponent().inject(this);
        BookDetailsViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(BookDetailsViewModel.class);


        viewModel.init(bookId);
        viewModel.getBook().observe(this, book -> {
            displayBookInfo(book);
        });
    }

    private void displayBookInfo(Book book) {
        titleTextView.setText(book.getTitle());
        descriptionTextView.setText(book.getDescription());
        Picasso.get().load(book.getImageUrl()).into(imageView);
    }
}