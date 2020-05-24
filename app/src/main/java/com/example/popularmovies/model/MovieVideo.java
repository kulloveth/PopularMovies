package com.example.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieVideo implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("results")
    @Expose
    private List<MovieVideoResponse> movieVideoResponseList = null;

    public MovieVideo() {
    }

    public MovieVideo(int id, List<MovieVideoResponse> movieVideoResponseList) {
        this.id = id;
        this.movieVideoResponseList = movieVideoResponseList;
    }

    protected MovieVideo(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<MovieVideo> CREATOR = new Creator<MovieVideo>() {
        @Override
        public MovieVideo createFromParcel(Parcel in) {
            return new MovieVideo(in);
        }

        @Override
        public MovieVideo[] newArray(int size) {
            return new MovieVideo[size];
        }
    };

    public int getId() {
        return id;
    }

    public List<MovieVideoResponse> getMovieVideoResponseList() {
        return movieVideoResponseList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }
}
