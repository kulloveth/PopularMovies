package com.kulloveth.popularmovies.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "favorite-table")
public class FavoriteEntity {

    @PrimaryKey
    @ColumnInfo(name = "movieId")
    private int id;

    @ColumnInfo(name = "title")
    private String originalTitle;

    @ColumnInfo(name = "imageUrl")
    private String posterPath;

    @Ignore
    public FavoriteEntity() {
    }

    public FavoriteEntity(int id, String originalTitle, String posterPath) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
    }

    protected FavoriteEntity(Parcel in) {
        id = in.readInt();
        originalTitle = in.readString();
        posterPath = in.readString();
    }


    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }



    @Override
    public String toString() {
        return "FavoriteEntity{" +
                "id=" + id +
                ", originalTitle='" + originalTitle + '\'' +
                ", posterPath='" + posterPath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoriteEntity)) return false;
        FavoriteEntity that = (FavoriteEntity) o;
        return getId() == that.getId() &&
                getOriginalTitle().equals(that.getOriginalTitle()) &&
                getPosterPath().equals(that.getPosterPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOriginalTitle(), getPosterPath());
    }
}
