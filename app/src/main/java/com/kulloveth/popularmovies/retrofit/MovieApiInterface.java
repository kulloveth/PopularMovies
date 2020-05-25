package com.kulloveth.popularmovies.retrofit;

import com.kulloveth.popularmovies.ApiUtils;
import com.kulloveth.popularmovies.model.MovieResponse;
import com.kulloveth.popularmovies.model.MovieReviewsResponse;
import com.kulloveth.popularmovies.model.MovieVideosResponse;

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
