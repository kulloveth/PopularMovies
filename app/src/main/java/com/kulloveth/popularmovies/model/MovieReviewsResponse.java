package com.kulloveth.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieReviewsResponse implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("results")
    @Expose
    private List<MovieReview> movieReviewList = null;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("total_results")
    @Expose
    private int totalResults;

    public MovieReviewsResponse() {
    }

    public MovieReviewsResponse(int id, int page, List<MovieReview> movieReviewList, int totalPages, int totalResults) {
        this.id = id;
        this.page = page;
        this.movieReviewList = movieReviewList;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    protected MovieReviewsResponse(Parcel in) {
        id = in.readInt();
        page = in.readInt();
        totalPages = in.readInt();
        totalResults = in.readInt();
    }

    public static final Creator<MovieReviewsResponse> CREATOR = new Creator<MovieReviewsResponse>() {
        @Override
        public MovieReviewsResponse createFromParcel(Parcel in) {
            return new MovieReviewsResponse(in);
        }

        @Override
        public MovieReviewsResponse[] newArray(int size) {
            return new MovieReviewsResponse[size];
        }
    };

    public int getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public List<MovieReview> getMovieReviewList() {
        return movieReviewList;
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
