package com.example.popularmovies.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.ProgressListener;
import com.example.popularmovies.R;
import com.example.popularmovies.databinding.ActivityMainBinding;
import com.example.popularmovies.model.Movie;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final String MOVIE_KEY = "movie";

    private ActivityMainBinding binding;
    private MainActivityVieModel mainActivityVieModel;
    private MovieAdapter adapter;
    List<Movie> movieList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle(getString(R.string.popular_movies));
        adapter = new MovieAdapter();
        RecyclerView recyclerView = binding.moviewsRv;
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
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    Snackbar.make(getWindow().getDecorView(), getString(R.string.no_internet_message), Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void showMovies() {
                binding.progressBar.setVisibility(View.INVISIBLE);
                binding.noIntrnetTv.setVisibility(View.GONE);
            }

            @Override
            public void showNoInternet() {
                binding.progressBar.setVisibility(View.INVISIBLE);
                Snackbar.make(getWindow().getDecorView(), getString(R.string.no_internet_message), Snackbar.LENGTH_SHORT).show();
            }


        });

        getPopularMovie();

    }

    //observe popular movies from viewmodel
    private void getPopularMovie() {
        mainActivityVieModel.getPopularMovie().observe(this,
                movies -> {
                    movieList = movies;
                    adapter.submitList(movieList);
                });
    }

    //observe top rated movies from viewmodel
    private void getTopRatedMovie() {
        mainActivityVieModel.getTopRatedMovie().observe(this, adapter::submitList);
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
            case R.id.popular: {
                setTitle(getString(R.string.popular_movies));
                getPopularMovie();
                return true;
            }
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
