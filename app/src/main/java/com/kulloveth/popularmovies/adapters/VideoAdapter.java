package com.kulloveth.popularmovies.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kulloveth.popularmovies.ApiUtils;
import com.kulloveth.popularmovies.R;
import com.kulloveth.popularmovies.databinding.TrailerItemBinding;
import com.kulloveth.popularmovies.model.MovieVideo;
import com.squareup.picasso.Picasso;

public class VideoAdapter extends ListAdapter<MovieVideo, VideoAdapter.MovieVideoViewHolder> {
    public ItemClickedListener mItemClickedListener;

    public VideoAdapter() {
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
        MovieVideo video = getItem(position);
        String youtubeKey = video.getKey();
        String imageUrl = ApiUtils.YOUTUBE_BASE_IMAGE_URL + youtubeKey + ApiUtils.YOUTUBE_JPG_ENDING_URL;
        Picasso.get().load(imageUrl).resize(600,400)
                .placeholder(holder.itemView.getContext().getDrawable(R.drawable.ic_slideshow))
                .into(holder.posterImage);
        holder.trailerName.setText(video.getName());
        holder.posterImage.setOnClickListener(v -> mItemClickedListener.onItemClicked(video, position));


    }

    class MovieVideoViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImage;
        private TextView trailerName;

        MovieVideoViewHolder(TrailerItemBinding binding) {
            super(binding.getRoot());
            posterImage = binding.trailerPoster;
            trailerName = binding.trailerNameTv;
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

    public interface ItemClickedListener {
        void onItemClicked(MovieVideo movie, int position);
    }
}
