package com.devcolibri.booksapp.di;

import com.devcolibri.booksapp.DetailsActivity;

import dagger.Component;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent
public interface BookDetailsComponent {
    void inject(DetailsActivity detailsActivity);
}
