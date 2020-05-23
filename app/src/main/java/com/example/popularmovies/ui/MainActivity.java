package com.example.popularmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.popularmovies.R;
import com.example.popularmovies.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String MOVIE_KEY = "movie";
    private ActivityMainBinding binding;
    private MainActivityVieModel mainActivityVieModel;
    private MovieAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle(getString(R.string.popular_movies));
        adapter = new MovieAdapter();
        RecyclerView recyclerView = binding.moviewsRv;
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        mainActivityVieModel = new ViewModelProvider(this).get(MainActivityVieModel.class);

       getPopularMovie();
        adapter.setmItemClickedListener((movie, position) -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(MOVIE_KEY, movie);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "" + movie.getmTitle(), Toast.LENGTH_SHORT).show();
        });

    }

    private void getPopularMovie() {
        mainActivityVieModel.getPopularMovie().observe(this, adapter::submitList);
    }

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
}
