package com.kulloveth.popularmovies.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "favorite-table")
public class FavoriteEntity implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "movieId")
    private int id;

    @ColumnInfo(name = "title")
    private String originalTitle;

    @ColumnInfo(name = "imageUrl")
    private String posterPath;

    @ColumnInfo(name = "releasedate")
    private String releaseDate;

    @ColumnInfo(name = "vote_average")
    private double userRating;

    @ColumnInfo(name = "overview")
    private String synopsis;

    @Ignore
    public FavoriteEntity() {
    }

    public FavoriteEntity(int id, String originalTitle, String posterPath, String releaseDate, double userRating, String synopsis) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.userRating = userRating;
        this.synopsis = synopsis;
    }

    protected FavoriteEntity(Parcel in) {
        id = in.readInt();
        originalTitle = in.readString();
        posterPath = in.readString();
        releaseDate = in.readString();
        userRating = in.readDouble();
        synopsis = in.readString();
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getUserRating() {
        return userRating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoriteEntity)) return false;
        FavoriteEntity that = (FavoriteEntity) o;
        return getId() == that.getId() &&
                Double.compare(that.getUserRating(), getUserRating()) == 0 &&
                getOriginalTitle().equals(that.getOriginalTitle()) &&
                getPosterPath().equals(that.getPosterPath()) &&
                getReleaseDate().equals(that.getReleaseDate()) &&
                getSynopsis().equals(that.getSynopsis());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOriginalTitle(), getPosterPath(), getReleaseDate(), getUserRating(), getSynopsis());
    }

    @Override
    public String toString() {
        return "FavoriteEntity{" +
                "id=" + id +
                ", originalTitle='" + originalTitle + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", userRating=" + userRating +
                ", synopsis='" + synopsis + '\'' +
                '}';
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
        dest.writeString(releaseDate);
        dest.writeDouble(userRating);
        dest.writeString(synopsis);
    }
}