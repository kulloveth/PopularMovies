package com.example.popularmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.popularmovies.databinding.ActivityMainBinding;
import com.example.popularmovies.model.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MOVIE_KEY = "movie";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MovieAdapter adapter = new MovieAdapter();
        RecyclerView recyclerView = binding.moviewsRv;
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        MainActivityVieModel mainActivityVieModel = new ViewModelProvider(this).get(MainActivityVieModel.class);

        mainActivityVieModel.getPopularMovie().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.submitList(movies);
            }
        });
        adapter.setmItemClickedListener((movie, position) -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(MOVIE_KEY, movie);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "" + movie.getmTitle(), Toast.LENGTH_SHORT).show();
        });

    }

}
