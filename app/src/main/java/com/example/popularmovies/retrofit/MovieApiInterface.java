package com.example.popularmovies.retrofit;

import com.example.popularmovies.ApiUtils;
import com.example.popularmovies.model.MovieResponse;
import com.example.popularmovies.model.MovieReview;
import com.example.popularmovies.model.MovieReviewsResponse;
import com.example.popularmovies.model.MovieVideo;
import com.example.popularmovies.model.MovieVideosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MovieApiInterface {

    //get popular movies from url
    @GET(ApiUtils.POPULAR_MOVIES_URL)
    Call<MovieResponse> getPopularMovies();

    //get top rated movies url
    @GET(ApiUtils.TOP_RATED_MOVIES_URL)
    Call<MovieResponse> getTopRatedMovies();

    @GET
    Call<MovieVideosResponse> getMovieVideo(@Url String videoUrl);

    @GET
    Call<MovieReviewsResponse> getMovieReview(@Url String reviewUrl);


}
