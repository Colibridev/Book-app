package com.devcolibri.booksapp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BooksRecyclerAdapter extends RecyclerView.Adapter<BooksRecyclerAdapter.BookViewHolder> {

    private List<Book> books;
    private OnBookClickListener onBookClickListener;

    public BooksRecyclerAdapter(OnBookClickListener onBookClickListener) {
        books = new ArrayList<>();
        this.onBookClickListener = onBookClickListener;
    }


    public interface OnBookClickListener {
        void onItemClick(Book book);
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.book_item_view, parent, false);

        return new BookViewHolder(view);
    }


    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book item = books.get(position);
        holder.bind(item);
    }


    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setItems(Collection<Book> items) {
        if(!books.isEmpty()) books.clear();

        books.addAll(items);
        notifyDataSetChanged();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private ImageView imageView;

        public BookViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.title_text_view);
            imageView = itemView.findViewById(R.id.image_view);

            itemView.setOnClickListener(v -> {
                Book book = books.get(getLayoutPosition());
                onBookClickListener.onItemClick(book);
            });
        }

        public void bind(final Book model) {
            titleTextView.setText(model.getTitle());
            Picasso.get()
                   .load(model.getImageUrl())
                   .into(imageView);
        }
    }


}