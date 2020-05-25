package com.kulloveth.popularmovies.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kulloveth.popularmovies.ProgressListener;
import com.kulloveth.popularmovies.R;
import com.kulloveth.popularmovies.adapters.FavoriteAdapter;
import com.kulloveth.popularmovies.adapters.MovieAdapter;

import com.kulloveth.popularmovies.databinding.ActivityMainBinding;
import com.kulloveth.popularmovies.db.FavoriteEntity;
import com.kulloveth.popularmovies.model.Movie;
import com.kulloveth.popularmovies.ui.details.DetailActivity;
import com.google.android.material.snackbar.Snackbar;
import com.kulloveth.popularmovies.ui.favorite.FavoriteViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final String MOVIE_KEY = "movie";

    private ActivityMainBinding binding;
    private MainActivityVieModel mainActivityVieModel;
    private MovieAdapter adapter;
    List<Movie> movieList = new ArrayList<>();
    List<FavoriteEntity> favoriteList = new ArrayList<>();
    FavoriteAdapter favoriteAdapter;
    // FavoriteEntity favoriteEntity = new FavoriteEntity();
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle(getString(R.string.popular_movies));
        adapter = new MovieAdapter();
        favoriteAdapter = new FavoriteAdapter();
        recyclerView = binding.moviewsRv;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        mainActivityVieModel = new ViewModelProvider(this).get(MainActivityVieModel.class);


        adapter.setmItemClickedListener((movie, position) -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(MOVIE_KEY, movie);
            startActivity(intent);
        });


        //implement interface to listen to changes
        mainActivityVieModel.setListener(new ProgressListener() {

            @Override
            public void showLoading() {
                if (isConnected()) {
                    binding.progressBar.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    promptUserForNetwork();
                }
            }

            @Override
            public void showMovies() {
                binding.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void showNoInternet() {
                promptUserForNetwork();
            }


        });

        getPopularMovie();

    }

    //observe popular movies from viewModel
    private void getPopularMovie() {
        mainActivityVieModel.getPopularMovie().observe(this,
                movies -> {
                    movieList = movies;
                    adapter.submitList(movieList);
                });
        recyclerView.setAdapter(adapter);
    }

    //observe top rated movies from viewModel
    private void getTopRatedMovie() {
        mainActivityVieModel.getTopRatedMovie().observe(this, adapter::submitList);
        recyclerView.setAdapter(adapter);
    }

    void promptUserForNetwork() {
        binding.progressBar.setVisibility(View.INVISIBLE);
        Snackbar.make(getWindow().getDecorView(), getString(R.string.no_internet_message), Snackbar.LENGTH_SHORT).show();
    }

    //observe favorite movies
    void favMovies() {
        mainActivityVieModel.getFav().observe(this, favoriteEntities -> {
            Log.d("Favorites", "onChanged: " + favoriteEntities);
            favoriteAdapter.submitList(favoriteEntities);
        });
        binding.progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(favoriteAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.top_rated:
                setTitle(getString(R.string.top_rated));
                getTopRatedMovie();
                return true;
            case R.id.popular:
                setTitle(getString(R.string.popular_movies));
                getPopularMovie();
                return true;

            case R.id.favorite:
                setTitle(getString(R.string.favorite_movies));
                favMovies();

        }
        return false;
    }

    void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    //check if user has internet connection
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }



}
