package com.kulloveth.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Movie implements Parcelable {



    @SerializedName("id")
    @Expose
    private int movieId;

    @SerializedName("release_date")
    @Expose
    private String mReleaseDate;

    @SerializedName("original_title")
    @Expose
    private String mTitle;

    @SerializedName("poster_path")
    @Expose
    private String mThumbnail;

    @SerializedName("vote_average")
    @Expose
    private double mUserRating;

    @SerializedName("overview")
    @Expose
    private String mSynopsis;

    public Movie(int movieId, String mReleaseDate, String mTitle, String mThumbnail, double mUserRating, String mSynopsis) {
        this.movieId = movieId;
        this.mReleaseDate = mReleaseDate;
        this.mTitle = mTitle;
        this.mThumbnail = mThumbnail;
        this.mUserRating = mUserRating;
        this.mSynopsis = mSynopsis;
    }

    protected Movie(Parcel in) {
        movieId = in.readInt();
        mReleaseDate = in.readString();
        mTitle = in.readString();
        mThumbnail = in.readString();
        mUserRating = in.readDouble();
        mSynopsis = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getMovieId() {
        return movieId;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmThumbnail() {
        return mThumbnail;
    }

    public double getmUserRating() {
        return mUserRating;
    }

    public String getmSynopsis() {
        return mSynopsis;
    }

    public Movie() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(movieId);
        dest.writeString(mReleaseDate);
        dest.writeString(mTitle);
        dest.writeString(mThumbnail);
        dest.writeDouble(mUserRating);
        dest.writeString(mSynopsis);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return getMovieId() == movie.getMovieId() &&
                Double.compare(movie.getmUserRating(), getmUserRating()) == 0 &&
                getmReleaseDate().equals(movie.getmReleaseDate()) &&
                getmTitle().equals(movie.getmTitle()) &&
                getmThumbnail().equals(movie.getmThumbnail()) &&
                getmSynopsis().equals(movie.getmSynopsis());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieId(), getmReleaseDate(), getmTitle(), getmThumbnail(), getmUserRating(), getmSynopsis());
    }
}
