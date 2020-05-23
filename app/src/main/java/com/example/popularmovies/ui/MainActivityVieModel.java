package com.example.popularmovies.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.popularmovies.ApiUtils;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.MovieResponse;
import com.example.popularmovies.retrofit.MovieApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityVieModel extends ViewModel {
    private static final String TAG = MainActivityVieModel.class.getSimpleName();

    private MutableLiveData<List<Movie>> movieLiveData;
    private MovieApiInterface movieApiInterface;

    public MainActivityVieModel() {
        movieApiInterface = ApiUtils.getMovieApiInterface();
        movieLiveData = new MutableLiveData<>();
    }

    LiveData<List<Movie>> getPopularMovie() {
        movieApiInterface.getPopularMovies().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    movieLiveData.setValue(response.body().getMovies());
                } else {
                    Log.e(TAG, "onResponse: Error fetching data" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "error" + t.getMessage());
            }
        });
        return movieLiveData;
    }
}
