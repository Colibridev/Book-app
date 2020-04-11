package com.devcolibri.booksapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookService {
    String BASE_URL = "https://api.npoint.io/";
    @GET("0f14812045073a3052d4")
    Call<List<Book>> getBooks();

}