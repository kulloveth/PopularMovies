package com.kulloveth.popularmovies.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(FavoriteEntity favoriteEntity);

    @Query("Select * from `favorite-table`")
    public void fetch();

    @Delete
    public void delete(FavoriteEntity favoriteEntity);
}
