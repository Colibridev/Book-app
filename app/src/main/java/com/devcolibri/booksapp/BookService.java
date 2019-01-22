package com.devcolibri.booksapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookService {
    String BASE_URL = "https://api.myjson.com/bins/";
    @GET("cckd0")
    Call<List<Book>> getBooks();

}