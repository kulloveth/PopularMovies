package com.example.popularmovies;

import java.io.Serializable;

public class Movie implements Serializable {
    private int movieId;
    private String mReleaseDate;
    private String mTitle;
    private String mThumbnail;
    private int mUserRating;
    private String mSynopsis;

    public Movie(int movieId, String mReleaseDate, String mTitle, String mThumbnail, int mUserRating, String mSynopsis) {
        this.movieId = movieId;
        this.mReleaseDate = mReleaseDate;
        this.mTitle = mTitle;
        this.mThumbnail = mThumbnail;
        this.mUserRating = mUserRating;
        this.mSynopsis = mSynopsis;
    }

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

    public int getmUserRating() {
        return mUserRating;
    }

    public String getmSynopsis() {
        return mSynopsis;
    }

    public Movie() {
    }

}
