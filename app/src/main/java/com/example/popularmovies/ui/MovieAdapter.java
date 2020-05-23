package com.example.popularmovies.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.popularmovies.ApiUtils;
import com.example.popularmovies.databinding.MovieItemBinding;
import com.example.popularmovies.model.Movie;

public class MovieAdapter extends ListAdapter<Movie, MovieAdapter.MovieViewHolder> {
    private MovieItemBinding binding;

    protected MovieAdapter() {
        super(sCallback);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MovieItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        String imageUrl = ApiUtils.BASE_IMAGE_PATH+movie.getmThumbnail();
        Glide.with(holder.itemView.getContext())
                .load(imageUrl).into(holder.posterImage);


    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImage;

        public MovieViewHolder(MovieItemBinding binding) {
            super(binding.getRoot());
            posterImage = binding.moviePoster;
        }
    }

    private static DiffUtil.ItemCallback<Movie> sCallback = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getMovieId()==(newItem.getMovieId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    };
}
