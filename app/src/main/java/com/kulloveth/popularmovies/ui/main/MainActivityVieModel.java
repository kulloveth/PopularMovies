package com.kulloveth.popularmovies.ui.main;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kulloveth.popularmovies.ApiUtils;
import com.kulloveth.popularmovies.ProgressListener;
import com.kulloveth.popularmovies.db.FavoriteDatabase;
import com.kulloveth.popularmovies.db.FavoriteEntity;
import com.kulloveth.popularmovies.model.Movie;
import com.kulloveth.popularmovies.model.MovieResponse;
import com.kulloveth.popularmovies.retrofit.MovieApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityVieModel extends AndroidViewModel {
    private static final String TAG = MainActivityVieModel.class.getSimpleName();

    private MutableLiveData<List<Movie>> popularMovieLiveData;
    private MutableLiveData<List<Movie>> topRatedMovieLiveData;
    private MovieApiInterface movieApiInterface;
    private ProgressListener listener;
    private FavoriteDatabase favoriteDatabase;

    void setListener(ProgressListener listener) {
        this.listener = listener;
    }

    public MainActivityVieModel(Application application) {
        super(application);
        movieApiInterface = ApiUtils.getMovieApiInterface();
        popularMovieLiveData = new MutableLiveData<>();
        topRatedMovieLiveData = new MutableLiveData<>();
        favoriteDatabase = FavoriteDatabase.getDatabase(application);
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

    //fetch favorites from Room
    LiveData<List<FavoriteEntity>> getFav() {
        return favoriteDatabase.favoriteDao().fetch();

    }

}
