package com.kulloveth.popularmovies.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite-table")
public class FavoriteEntity implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "movieId")
    private int id;

    @ColumnInfo(name = "title")
    private String originalTitle;

    @ColumnInfo(name = "imageUrl")
    private String posterPath;

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

    public static final Creator<FavoriteEntity> CREATOR = new Creator<FavoriteEntity>() {
        @Override
        public FavoriteEntity createFromParcel(Parcel in) {
            return new FavoriteEntity(in);
        }

        @Override
        public FavoriteEntity[] newArray(int size) {
            return new FavoriteEntity[size];
        }
    };

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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(originalTitle);
        dest.writeString(posterPath);
    }
}
