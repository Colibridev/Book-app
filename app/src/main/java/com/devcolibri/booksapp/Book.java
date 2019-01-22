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
}
