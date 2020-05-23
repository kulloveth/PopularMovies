package com.example.popularmovies;

import java.io.Serializable;

public class Movie implements Serializable {
    private String movieId;
    private String mReleaseDate;
    private String mTitle;
    private String mThumbnail;
    private String mUserRating;
    private String mSynopsis;

    public String getMovieId() {
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

    public String getmUserRating() {
        return mUserRating;
    }

    public String getmSynopsis() {
        return mSynopsis;
    }

    public Movie(String movieId, String mReleaseDate, String mTitle, String mThumbnail, String mUserRating, String mSynopsis) {
        this.movieId = movieId;
        this.mReleaseDate = mReleaseDate;
        this.mTitle = mTitle;
        this.mThumbnail = mThumbnail;
        this.mUserRating = mUserRating;
        this.mSynopsis = mSynopsis;
    }

    public Movie() {
    }

}
