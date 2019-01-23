package com.devcolibri.booksapp.di;

import com.devcolibri.booksapp.DetailsActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class)
public interface BookDetailsComponent {
    void inject(DetailsActivity detailsActivity);
}
