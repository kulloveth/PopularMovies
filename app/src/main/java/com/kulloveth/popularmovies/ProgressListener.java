package com.kulloveth.popularmovies;

public interface ProgressListener {
    void showLoading();
    void showMovies();
    void showNoInternet();
}
