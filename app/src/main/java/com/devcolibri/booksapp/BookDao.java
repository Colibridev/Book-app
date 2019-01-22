package com.devcolibri.booksapp;

import java.util.List;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface BookDao {
    @Insert
    void insertBooks(List<Book> books);

    @Query("SELECT * FROM Book")
    List<Book> getBooks();

    @Query("SELECT * FROM Book WHERE id = :id LIMIT 1")
    Book getBook(long id);
}