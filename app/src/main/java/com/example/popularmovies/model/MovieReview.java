package com.example.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieReview implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("results")
    @Expose
    private List<MovieReviewResponse> movieReviewResponseList = null;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("total_results")
    @Expose
    private int totalResults;

    public MovieReview() {
    }

    public MovieReview(int id, int page, List<MovieReviewResponse> movieReviewResponseList, int totalPages, int totalResults) {
        this.id = id;
        this.page = page;
        this.movieReviewResponseList = movieReviewResponseList;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    protected MovieReview(Parcel in) {
        id = in.readInt();
        page = in.readInt();
        totalPages = in.readInt();
        totalResults = in.readInt();
    }

    public static final Creator<MovieReview> CREATOR = new Creator<MovieReview>() {
        @Override
        public MovieReview createFromParcel(Parcel in) {
            return new MovieReview(in);
        }

        @Override
        public MovieReview[] newArray(int size) {
            return new MovieReview[size];
        }
    };

    public int getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public List<MovieReviewResponse> getMovieReviewResponseList() {
        return movieReviewResponseList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(page);
        dest.writeInt(totalPages);
        dest.writeInt(totalResults);
    }
}
