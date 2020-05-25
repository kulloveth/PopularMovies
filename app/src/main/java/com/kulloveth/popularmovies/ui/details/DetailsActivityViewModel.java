package com.kulloveth.popularmovies.ui.details;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kulloveth.popularmovies.ApiUtils;
import com.kulloveth.popularmovies.ProgressListener;
import com.kulloveth.popularmovies.db.FavoriteDatabase;
import com.kulloveth.popularmovies.db.FavoriteEntity;
import com.kulloveth.popularmovies.model.MovieReview;
import com.kulloveth.popularmovies.model.MovieReviewsResponse;
import com.kulloveth.popularmovies.model.MovieVideo;
import com.kulloveth.popularmovies.model.MovieVideosResponse;
import com.kulloveth.popularmovies.retrofit.MovieApiInterface;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivityViewModel extends AndroidViewModel {
    private static final String TAG = DetailsActivityViewModel.class.getSimpleName();

    private MutableLiveData<List<MovieVideo>> trailerVideosLivedata;
    private MutableLiveData<List<MovieReview>> reviewVideosLivedata;
    private MovieApiInterface movieApiInterface;
    private ProgressListener listener;
    private FavoriteDatabase favoriteDatabase;
    private CompositeDisposable compositeDisposable;

    void setListener(ProgressListener listener) {
        this.listener = listener;
    }




    public DetailsActivityViewModel(@NonNull Application application) {
        super(application);
        movieApiInterface = ApiUtils.getMovieApiInterface();
        trailerVideosLivedata = new MutableLiveData<>();
        reviewVideosLivedata = new MutableLiveData<>();
        favoriteDatabase = FavoriteDatabase.getDatabase(application);
        compositeDisposable = new CompositeDisposable();

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


    LiveData<List<MovieReview>> getMovieReviiew(String url) {

        movieApiInterface.getMovieReview(url).enqueue(new Callback<MovieReviewsResponse>() {
            @Override
            public void onResponse(Call<MovieReviewsResponse> call, Response<MovieReviewsResponse> response) {
                if (response.isSuccessful()) {
                    reviewVideosLivedata.setValue(response.body().getMovieReviewList());
                    Log.d(TAG, "onResponse: review loade");
                } else {
                    Log.d(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieReviewsResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: videoResponseError" + t.getMessage());
            }
        });
        return reviewVideosLivedata;
    }


     void insertFavorite(FavoriteEntity favoriteEntity) {
        Completable completable = favoriteDatabase.favoriteDao().
                insert(favoriteEntity).subscribeOn(Schedulers.io());

        compositeDisposable.add(completable.subscribe());

    }
}
