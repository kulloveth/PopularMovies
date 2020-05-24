package com.example.popularmovies.retrofit;

import com.example.popularmovies.ApiUtils;
import com.example.popularmovies.model.MovieResponse;
import com.example.popularmovies.model.MovieReviewResponse;
import com.example.popularmovies.model.MovieVideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiInterface {

    //get popular movies from url
    @GET(ApiUtils.POPULAR_MOVIES_URL)
    Call<MovieResponse> getPopularMovies();

    //get top rated movies url
    @GET(ApiUtils.TOP_RATED_MOVIES_URL)
    Call<MovieResponse> getTopRatedMovies();

    @GET
    Call<MovieVideoResponse> getMovieVideo();

    @GET
    Call<MovieReviewResponse> getMoviewReview();


}
