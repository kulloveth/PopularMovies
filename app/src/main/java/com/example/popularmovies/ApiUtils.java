package com.example.popularmovies;

import com.example.popularmovies.retrofit.MovieApiInterface;
import com.example.popularmovies.retrofit.RetrofitClient;

public class ApiUtils {

    //themoviedb.org api url
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    //api key param
    private static final String API_KEY_PARAM = "?api_key=";

    //api key
    private static final String API_KEY = "";//get your api key from themoviedb.org

    //popular movies url
    public static final String POPULAR_MOVIES_URL = BASE_URL + "popular" + API_KEY_PARAM + API_KEY;

    //favorite movies url
    public static final String TOP_RATED_MOVIES_URL = BASE_URL + "top_rated" + API_KEY_PARAM + API_KEY;

    public static final String BASE_IMAGE_PATH = "http://image.tmdb.org/t/p/w185/";

    public static MovieApiInterface getMovieApiInterface() {
        return RetrofitClient.getRetrofitClient(ApiUtils.BASE_URL).create(MovieApiInterface.class);
    }

}
