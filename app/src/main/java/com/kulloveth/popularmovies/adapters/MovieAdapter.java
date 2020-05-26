package com.kulloveth.popularmovies.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kulloveth.popularmovies.ApiUtils;
import com.kulloveth.popularmovies.R;
import com.kulloveth.popularmovies.databinding.MovieItemBinding;
import com.kulloveth.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieAdapter extends ListAdapter<Movie, MovieAdapter.MovieViewHolder> {
    public ItemClickedListener mItemClickedListener;


    public MovieAdapter() {
        super(sCallback);
    }

    public void setmItemClickedListener(ItemClickedListener mItemClickedListener) {
        this.mItemClickedListener = mItemClickedListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding binding = MovieItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        String imageUrl = ApiUtils.BASE_IMAGE_PATH + movie.getmThumbnail();
        Picasso.get().load(imageUrl)
                .error(holder.itemView.getContext().getDrawable(R.drawable.ic_slideshow))
                .placeholder(holder.itemView.getContext().getDrawable(R.drawable.ic_slideshow))
                .into(holder.posterImage);

        holder.posterImage.setOnClickListener(v -> mItemClickedListener.onItemClicked(movie, position));


    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImage;

        MovieViewHolder(MovieItemBinding binding) {
            super(binding.getRoot());
            posterImage = binding.moviePoster;
        }

    }

    private static DiffUtil.ItemCallback<Movie> sCallback = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getMovieId() == (newItem.getMovieId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    };

    public interface ItemClickedListener {
        void onItemClicked(Movie movie, int position);
    }
}
