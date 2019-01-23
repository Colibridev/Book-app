package com.devcolibri.booksapp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    private TextView titleTextView;
    private TextView descriptionTextView;
    private ImageView imageView;

    private BookDao bookDao;

    public static String BOOK_ID_EXTRA = "bookId";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        titleTextView = findViewById(R.id.title_text_view);
        descriptionTextView = findViewById(R.id.description_text_view);
        imageView = findViewById(R.id.image_view);


        bookDao = App.getDb().getBookDao();

        long bookId = getIntent().getLongExtra(BOOK_ID_EXTRA, -1);
        if(bookId == -1) throw new IllegalArgumentException("Необходимо передать bookId параметр");


        loadBookInfo(bookId);
    }

    @SuppressLint("StaticFieldLeak")
    private void loadBookInfo(long bookId) {
        new AsyncTask<Void, Void, Book>() {
            @Override
            protected Book doInBackground(Void... voids) {
                return bookDao.getBook(bookId);
            }

            @Override
            protected void onPostExecute(Book book) {
                displayBookInfo(book);
            }
        }.execute();
    }

    private void displayBookInfo(Book book) {
        titleTextView.setText(book.getTitle());
        descriptionTextView.setText(book.getDescription());
        Picasso.get().load(book.getImageUrl()).into(imageView);
    }
}