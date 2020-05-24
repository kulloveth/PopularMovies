package com.example.popularmovies;

public interface ProgressListener {
    void showLoading();
    void showMovies();
    void showNoInternet();
}
