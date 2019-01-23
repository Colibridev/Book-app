package com.devcolibri.booksapp.di;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    BookListComponent plusBookListComponent();
    BookDetailsComponent plusBookDetailsComponent();
}
