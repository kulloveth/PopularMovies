package com.example.popularmovies.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.popularmovies.ApiUtils;
import com.example.popularmovies.ProgressListener;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.MovieResponse;
import com.example.popularmovies.retrofit.MovieApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityVieModel extends ViewModel {
    private static final String TAG = MainActivityVieModel.class.getSimpleName();

    private MutableLiveData<List<Movie>> popularMovieLiveData;
    private MutableLiveData<List<Movie>> topRatedMovieLiveData;
    private MovieApiInterface movieApiInterface;
    private ProgressListener listener;

    void setListener(ProgressListener listener) {
        this.listener = listener;
    }

    public MainActivityVieModel() {
        movieApiInterface = ApiUtils.getMovieApiInterface();
        popularMovieLiveData = new MutableLiveData<>();
        topRatedMovieLiveData = new MutableLiveData<>();
    }

    //fetch popular movies from api
    LiveData<List<Movie>> getPopularMovie() {
        listener.showLoading();
        movieApiInterface.getPopularMovies().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    listener.showMovies();
                    List<Movie> movie = response.body().getMovies();
                    popularMovieLiveData.setValue(movie);


                } else {
                    Log.e(TAG, "onResponse: Error fetching data" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                listener.showNoInternet();
                Log.e(TAG, "error" + t.getMessage());
            }
        });
        return popularMovieLiveData;
    }


    //fetch toprated movies from api
    LiveData<List<Movie>> getTopRatedMovie() {
        listener.showLoading();
        movieApiInterface.getTopRatedMovies().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    listener.showMovies();
                    List<Movie> movie = response.body().getMovies();
                    topRatedMovieLiveData.setValue(movie);


                } else {
                    Log.e(TAG, "onResponse: Error fetching data" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                listener.showNoInternet();
                Log.e(TAG, "error" + t.getMessage());
            }
        });
        return topRatedMovieLiveData;
    }


}
