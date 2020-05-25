package com.kulloveth.popularmovies.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insert(FavoriteEntity favoriteEntity);

    @Query("Select * from `favorite-table`")
    public LiveData<List<FavoriteEntity>> fetch();

    @Delete
    public void delete(FavoriteEntity favoriteEntity);
}
