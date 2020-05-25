package com.kulloveth.popularmovies.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kulloveth.popularmovies.ApiUtils;
import com.kulloveth.popularmovies.databinding.MovieItemBinding;
import com.kulloveth.popularmovies.db.FavoriteEntity;

public class FavoriteAdapter extends ListAdapter<FavoriteEntity, FavoriteAdapter.FavoriteViewHolder> {
    public ItemClickedListener mItemClickedListener;


    public FavoriteAdapter() {
        super(sCallback);
    }

    public void setmItemClickedListener(ItemClickedListener mItemClickedListener) {
        this.mItemClickedListener = mItemClickedListener;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding binding = MovieItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FavoriteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        FavoriteEntity movie = getItem(position);
        String imageUrl = ApiUtils.BASE_IMAGE_PATH + movie.getPosterPath();
        // Picasso.get().load(imageUrl).into(holder.posterImage);
        Glide.with(holder.itemView.getContext())
                .load(imageUrl).into(holder.posterImage);
        holder.posterImage.setOnClickListener(v -> mItemClickedListener.onItemClicked(movie, position));


    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImage;

       FavoriteViewHolder(MovieItemBinding binding) {
            super(binding.getRoot());
            posterImage = binding.moviePoster;
        }

    }

    private static DiffUtil.ItemCallback<FavoriteEntity> sCallback = new DiffUtil.ItemCallback<FavoriteEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull FavoriteEntity oldItem, @NonNull FavoriteEntity newItem) {
            return oldItem.getId() == (newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull FavoriteEntity oldItem, @NonNull FavoriteEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    public interface ItemClickedListener {
        void onItemClicked(FavoriteEntity movie, int position);
    }
}
