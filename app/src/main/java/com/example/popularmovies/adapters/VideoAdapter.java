package com.example.popularmovies.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.databinding.TrailerItemBinding;
import com.example.popularmovies.model.MovieVideo;

public class VideoAdapter extends ListAdapter<MovieVideo, VideoAdapter.MovieVideoViewHolder> {
    private ItemClickedListener mItemClickedListener;

    VideoAdapter() {
        super(sCallback);
    }

    public void setmItemClickedListener(ItemClickedListener mItemClickedListener) {
        this.mItemClickedListener = mItemClickedListener;
    }

    @NonNull
    @Override
    public MovieVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TrailerItemBinding binding = TrailerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieVideoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieVideoViewHolder holder, int position) {
        MovieVideo movie = getItem(position);
//        String imageUrl = ApiUtils.BASE_IMAGE_PATH + movie.getmThumbnail();
//        Glide.with(holder.itemView.getContext())
//                .load(imageUrl).into(holder.posterImage);
//        holder.posterImage.setOnClickListener(v -> mItemClickedListener.onItemClicked(movie, position));


    }

    class MovieVideoViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImage;

        MovieVideoViewHolder(TrailerItemBinding binding) {
            super(binding.getRoot());
            posterImage = binding.trailerPoster;
        }

    }

    private static DiffUtil.ItemCallback<MovieVideo> sCallback = new DiffUtil.ItemCallback<MovieVideo>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieVideo oldItem, @NonNull MovieVideo newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull MovieVideo oldItem, @NonNull MovieVideo newItem) {
            return oldItem.equals(newItem);
        }
    };

    interface ItemClickedListener {
        void onItemClicked(MovieVideo movie, int position);
    }
}
