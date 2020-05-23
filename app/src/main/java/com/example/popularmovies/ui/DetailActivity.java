package com.example.popularmovies.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.popularmovies.ApiUtils;
import com.example.popularmovies.databinding.ActivityDetailBinding;
import com.example.popularmovies.model.Movie;

import static com.example.popularmovies.ui.MainActivity.MOVIE_KEY;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Movie movie = bundle.getParcelable(MOVIE_KEY);
            binding.titleTv.setText(movie.getmTitle());
            binding.ratingDetail.setText(String.valueOf(movie.getmUserRating()));
            binding.releasedDetail.setText(movie.getmReleaseDate());
            binding.synopsisTv.setText(movie.getmSynopsis());
            Glide.with(this).load(ApiUtils.BASE_IMAGE_PATH + movie.getmThumbnail()).into(binding.moviePoster);
        }

    }
}
