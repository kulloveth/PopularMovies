package com.example.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieVideosResponse implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("results")
    @Expose
    private List<MovieVideo> movieVideoList = null;

    public MovieVideosResponse() {
    }

    public MovieVideosResponse(int id, List<MovieVideo> movieVideoList) {
        this.id = id;
        this.movieVideoList = movieVideoList;
    }

    protected MovieVideosResponse(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<MovieVideosResponse> CREATOR = new Creator<MovieVideosResponse>() {
        @Override
        public MovieVideosResponse createFromParcel(Parcel in) {
            return new MovieVideosResponse(in);
        }

        @Override
        public MovieVideosResponse[] newArray(int size) {
            return new MovieVideosResponse[size];
        }
    };

    public int getId() {
        return id;
    }

    public List<MovieVideo> getMovieVideoList() {
        return movieVideoList;
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
