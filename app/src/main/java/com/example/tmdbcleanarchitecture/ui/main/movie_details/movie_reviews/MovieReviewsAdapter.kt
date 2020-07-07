package com.example.tmdbcleanarchitecture.ui.main.movie_details.movie_reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.tmdbcleanarchitecture.R
import com.example.tmdbcleanarchitecture.data.model.details.MovieReview
import com.example.tmdbcleanarchitecture.databinding.ItemMovieReviewBinding
import com.example.tmdbcleanarchitecture.ui.base.BaseRecyclerViewAdapter
import com.example.tmdbcleanarchitecture.ui.base.BaseViewHolder

class MovieReviewsAdapter(items: MutableList<MovieReview>) : BaseRecyclerViewAdapter<MovieReview>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemMovieReviewBinding: ItemMovieReviewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_movie_review, parent, false
        )
        return MovieReviewVHolder(itemMovieReviewBinding)
    }

    override fun getItemCount(): Int {
        return getItems().size
    }

    internal inner class MovieReviewVHolder(private var itemMovieReviewBinding: ItemMovieReviewBinding) : BaseViewHolder(itemMovieReviewBinding.root) {

        override fun onBind(position: Int) {
            itemMovieReviewBinding.movieReview = getItems()[position]
        }

    }
}
