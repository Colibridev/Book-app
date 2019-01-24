package com.devcolibri.booksapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Book {
    @PrimaryKey
    private long id;
    @SerializedName("image_url")
    private String imageUrl;
    private String title;
    private String description;

    public Book(long id, String imageUrl, String title, String description) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Book book = (Book) o;

        if (id != book.id) return false;
        if (!imageUrl.equals(book.imageUrl)) return false;
        if (!title.equals(book.title)) return false;
        return description.equals(book.description);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + imageUrl.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
