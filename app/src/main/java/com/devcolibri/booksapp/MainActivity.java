package com.devcolibri.booksapp;

import java.util.Arrays;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BooksRecyclerAdapter adapter = new BooksRecyclerAdapter((v) -> {
            // TODO добавить переход на следующую Activity
        });
        recyclerView.setAdapter(adapter);
        adapter.setItems(getDataList());
    }

    private List<Book> getDataList() {
        return Arrays.asList(
                new Book(1, "https://drive.google.com/uc?id=1OF9uLAkVCXQbqMyC2JF3XJ-JrZQd9Ssp",
                         "Чистый код: создание, анализ и рефакторинг",
                         "Чистый код - это круто"),

                new Book(2, "https://drive.google.com/uc?id=1t1iB3JG8peBOJmDFUGki3mh27rqcIimr",
                         "Совершенный код. Мастер-класс",
                         "Совершенный - это круто"),

                new Book(3, "https://drive.google.com/uc?id=1WvpeEDJBGUMmlycgc9ChswZtwFQUHQky",
                         "Приемы объектно-ориентированного проектирования. Паттерны проектирования",
                         "Паттерны - это круто."),

                new Book(4, "https://drive.google.com/uc?id=1CJAD4JQKKJyEkLSvZk3fN8wjiQ1x2Jn7",
                         "Искусство программирования. Том 1. Выпуск 1",
                         "Программирование - это круто"),

                new Book(5, "https://drive.google.com/uc?id=12m-GCnsdosdum8ocrcgVn5FzvTQUz_hi",
                         "Рефакторинг. Улучшение проекта существующего кода",
                         "Рефакторинг - это круто.")
        );
    }
}
