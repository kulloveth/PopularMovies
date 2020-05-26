package com.kulloveth.popularmovies.ui.favorite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.kulloveth.popularmovies.db.FavoriteDatabase;
import com.kulloveth.popularmovies.db.FavoriteEntity;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FavoriteViewModel extends AndroidViewModel {

    private FavoriteDatabase favoriteDatabase;
    private CompositeDisposable compositeDisposable;


    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        favoriteDatabase = FavoriteDatabase.getDatabase(application);
        compositeDisposable = new CompositeDisposable();

    }

    //delete favorite
    void delete(FavoriteEntity favoriteEntity) {
        Completable completable = favoriteDatabase.favoriteDao().delete(favoriteEntity).subscribeOn(Schedulers.io());
        compositeDisposable.add(completable.subscribe());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
