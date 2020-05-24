package com.example.popularmovies.ui.details;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.popularmovies.ApiUtils;
import com.example.popularmovies.ProgressListener;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.MovieVideo;
import com.example.popularmovies.model.MovieVideosResponse;
import com.example.popularmovies.retrofit.MovieApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivityViewModel extends ViewModel {
    private static final String TAG = DetailsActivityViewModel.class.getSimpleName();

    private MutableLiveData<List<MovieVideo>> trailerVideosLivedata;
    private MovieApiInterface movieApiInterface;
    private ProgressListener listener;

    void setListener(ProgressListener listener) {
        this.listener = listener;
    }

    public DetailsActivityViewModel() {
        movieApiInterface = ApiUtils.getMovieApiInterface();
        trailerVideosLivedata = new MutableLiveData<>();

    }

    LiveData<List<MovieVideo>> getMovieTrailer(String url) {

        movieApiInterface.getMovieVideo(url).enqueue(new Callback<MovieVideosResponse>() {
            @Override
            public void onResponse(Call<MovieVideosResponse> call, Response<MovieVideosResponse> response) {
                if (response.isSuccessful()) {
                    trailerVideosLivedata.setValue(response.body().getMovieVideoList());
                    Log.d(TAG, "onResponse: videos loade");
                } else {
                    Log.d(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieVideosResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: videoResponseError" + t.getMessage());
            }
        });
        return trailerVideosLivedata;
    }
}
