package com.kulloveth.popularmovies.ui.favorite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kulloveth.popularmovies.db.FavoriteDatabase;
import com.kulloveth.popularmovies.db.FavoriteEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FavoriteViewModel extends AndroidViewModel {

    private FavoriteDatabase favoriteDatabase;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<List<FavoriteEntity>> favoriteLivedata;


    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        favoriteDatabase = FavoriteDatabase.getDatabase(application);
        compositeDisposable = new CompositeDisposable();

    }



}
