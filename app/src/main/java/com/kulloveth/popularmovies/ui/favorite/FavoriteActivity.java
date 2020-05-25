package com.kulloveth.popularmovies.ui.favorite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kulloveth.popularmovies.ApiUtils;
import com.kulloveth.popularmovies.R;
import com.kulloveth.popularmovies.databinding.ActivityFavoriteBinding;
import com.kulloveth.popularmovies.db.FavoriteEntity;
import com.squareup.picasso.Picasso;

import static com.kulloveth.popularmovies.ui.main.MainActivity.FAV_KEY;
import static com.kulloveth.popularmovies.ui.main.MainActivity.MOVIE_KEY;

public class FavoriteActivity extends AppCompatActivity {


    ActivityFavoriteBinding binding;
    FavoriteEntity movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            movie = bundle.getParcelable(FAV_KEY);
            binding.titleTv.setText(movie.getOriginalTitle());
            binding.ratingDetail.setText(String.valueOf(movie.getUserRating()));
            binding.releasedDetail.setText(movie.getReleaseDate());
            binding.synopsisTv.setText(movie.getSynopsis());
            Picasso.get().load(ApiUtils.BASE_IMAGE_PATH + movie.getPosterPath()).into(binding.moviePoster);
        }
    }
}
