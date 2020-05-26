package com.kulloveth.popularmovies.ui.favorite;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.kulloveth.popularmovies.ApiUtils;
import com.kulloveth.popularmovies.R;
import com.kulloveth.popularmovies.databinding.ActivityFavoriteBinding;
import com.kulloveth.popularmovies.db.FavoriteEntity;
import com.squareup.picasso.Picasso;

import static com.kulloveth.popularmovies.ui.main.MainActivity.FAV_KEY;

public class FavoriteActivity extends AppCompatActivity {


    ActivityFavoriteBinding binding;
    FavoriteEntity movie;

    FavoriteViewModel favoriteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle(getString(R.string.favorite_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            movie = bundle.getParcelable(FAV_KEY);
            binding.titleTv.setText(movie.getOriginalTitle());
            binding.ratingDetail.setText(String.valueOf(movie.getUserRating()));
            binding.releasedDetail.setText(movie.getReleaseDate());
            binding.synopsisTv.setText(movie.getSynopsis());
            Picasso.get().load(ApiUtils.BASE_IMAGE_PATH + movie.getPosterPath()).into(binding.moviePoster);
        }
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        binding.favFab.setOnClickListener(v -> {
            deleteFavorite();
        });
    }

    void deleteFavorite() {
        favoriteViewModel.delete(new FavoriteEntity(movie.getId(), movie.getOriginalTitle(), movie.getPosterPath(), movie.getSynopsis(), movie.getUserRating(), movie.getReleaseDate()));
        Toast.makeText(this, movie.getOriginalTitle()+" removed", Toast.LENGTH_SHORT).show();
        finish();
    }


}
