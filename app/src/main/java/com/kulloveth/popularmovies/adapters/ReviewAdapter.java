package com.kulloveth.popularmovies.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.kulloveth.popularmovies.databinding.ReviewItemBinding;
import com.kulloveth.popularmovies.model.MovieReview;

public class ReviewAdapter extends ListAdapter<MovieReview, ReviewAdapter.MovieReviewViewHolder> {

    public ReviewAdapter() {
        super(sCallback);
    }

    @NonNull
    @Override
    public MovieReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReviewItemBinding binding = ReviewItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieReviewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewViewHolder holder, int position) {
        MovieReview review = getItem(position);
        holder.url.setText(review.getUrl());
        holder.content.setText(review.getContent());
        holder.author.setText(review.getAuthor());


    }

    class MovieReviewViewHolder extends RecyclerView.ViewHolder {
        private TextView author;
        private TextView content;
        private TextView url;

        MovieReviewViewHolder(ReviewItemBinding binding) {
            super(binding.getRoot());
            author = binding.authorTv;
            content = binding.contentTv;
            url = binding.urlTv;
        }

    }

    private static DiffUtil.ItemCallback<MovieReview> sCallback = new DiffUtil.ItemCallback<MovieReview>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieReview oldItem, @NonNull MovieReview newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull MovieReview oldItem, @NonNull MovieReview newItem) {
            return oldItem.equals(newItem);
        }
    };

}
