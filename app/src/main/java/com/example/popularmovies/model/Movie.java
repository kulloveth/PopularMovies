package com.example.popularmovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

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

}
