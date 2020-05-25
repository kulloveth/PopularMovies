package com.example.popularmovies.ui.details;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.popularmovies.ApiUtils;
import com.example.popularmovies.R;
import com.example.popularmovies.adapters.ReviewAdapter;
import com.example.popularmovies.adapters.VideoAdapter;
import com.example.popularmovies.databinding.ActivityDetailBinding;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.MovieReview;
import com.example.popularmovies.model.MovieVideo;

import java.util.ArrayList;
import java.util.List;

import static com.example.popularmovies.ui.main.MainActivity.MOVIE_KEY;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private DetailsActivityViewModel mViewModel;
    Movie movie;
    List<MovieReview> movieReviewList;
    List<MovieVideo> movieVideoList;
    ReviewAdapter reviewAdapter;
    RecyclerView reviewRv;
    VideoAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportActionBar().setTitle(getString(R.string.movie__detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        movieVideoList = new ArrayList<>();
        movieReviewList = new ArrayList<>();
        mViewModel = new ViewModelProvider(this).get(DetailsActivityViewModel.class);
        //get data from intent if it exist
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            movie = bundle.getParcelable(MOVIE_KEY);
            binding.titleTv.setText(movie.getmTitle());
            binding.ratingDetail.setText(String.valueOf(movie.getmUserRating()));
            binding.releasedDetail.setText(movie.getmReleaseDate());
            binding.synopsisTv.setText(movie.getmSynopsis());
            Glide.with(this).load(ApiUtils.BASE_IMAGE_PATH + movie.getmThumbnail()).into(binding.moviePoster);
        }

        adapter = new VideoAdapter();
        recyclerView = binding.videoRv;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        reviewAdapter = new ReviewAdapter();
        reviewRv = binding.reviewRv;
        reviewRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        reviewRv.setHasFixedSize(true);
        reviewRv.setAdapter(reviewAdapter);

        showTrailers();
        showReviews();


    }

    void showReviews() {
        movieReviewList = new ArrayList<>();
        String reviewUrl = ApiUtils.BASE_URL + movie.getMovieId()
                + ApiUtils.MOVIE_REVIEWS_PATH + ApiUtils.API_KEY_PARAM + ApiUtils.API_KEY;
        mViewModel.getMovieReviiew(reviewUrl).observe(this, new Observer<List<MovieReview>>() {
            @Override
            public void onChanged(List<MovieReview> movieReviews) {
                Log.d("review", "onChanged: " + movieReviews);
                movieReviewList = movieReviews;
                reviewAdapter.submitList(movieReviewList);
            }
        });
    }

    void showTrailers() {

        String videosUrl = ApiUtils.BASE_URL + movie.getMovieId()
                + ApiUtils.MOVIE_VIDEOS_PATH + ApiUtils.API_KEY_PARAM + ApiUtils.API_KEY;
        mViewModel.getMovieTrailer(videosUrl).observe(this, new Observer<List<MovieVideo>>() {
            @Override
            public void onChanged(List<MovieVideo> movieVideos) {
                movieVideoList = movieVideos;
                adapter.submitList(movieVideoList);
            }
        });

    }

}
