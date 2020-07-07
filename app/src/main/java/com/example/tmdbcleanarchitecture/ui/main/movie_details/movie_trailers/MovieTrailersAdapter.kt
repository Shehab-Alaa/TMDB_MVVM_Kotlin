package com.example.tmdbcleanarchitecture.ui.main.movie_details.movie_trailers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.tmdbcleanarchitecture.data.model.details.MovieTrailer
import com.example.tmdbcleanarchitecture.databinding.ItemEmptyMovieTrailerBinding
import com.example.tmdbcleanarchitecture.databinding.ItemMovieTrailerBinding
import com.example.tmdbcleanarchitecture.ui.base.BaseRecyclerViewAdapter
import com.example.tmdbcleanarchitecture.ui.base.BaseViewHolder
import com.example.tmdbcleanarchitecture.ui.main.movie_details.movie_trailers.MovieTrailerEmptyItemViewModel.MovieTrailerEmptyItemListener
import com.example.tmdbcleanarchitecture.ui.main.movie_details.movie_trailers.MovieTrailerItemViewModel.MovieTrailerClickListener
import com.example.tmdbcleanarchitecture.utils.AppConstants

class MovieTrailersAdapter(items: MutableList<MovieTrailer>, private val movieTrailersAdapterListener: MovieTrailersAdapterListener) : BaseRecyclerViewAdapter<MovieTrailer>(items) {


    override fun getItemViewType(position: Int): Int {
        return if (getItems().isNotEmpty()) AppConstants.VIEW_TYPE_NORMAL else AppConstants.VIEW_TYPE_EMPTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            AppConstants.VIEW_TYPE_NORMAL -> MovieTrailersViewHolder(
                ItemMovieTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> EmptyMovieTrailersViewHolder(ItemEmptyMovieTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    interface MovieTrailersAdapterListener {
        fun onMovieTrailersRetry()
        fun onMovieTrailerClick(movieTrailer: MovieTrailer?)
    }

    inner class MovieTrailersViewHolder(private val itemMovieTrailerBinding: ItemMovieTrailerBinding) :
        BaseViewHolder(itemMovieTrailerBinding.root), MovieTrailerClickListener {

        override fun onBind(position: Int) {
            itemMovieTrailerBinding.movieTrailerViewModel = MovieTrailerItemViewModel(getItems()[position], this)
            itemMovieTrailerBinding.executePendingBindings()
        }

        override fun onMovieTrailerClick(movieTrailer: MovieTrailer?) {
            movieTrailersAdapterListener.onMovieTrailerClick(movieTrailer)
        }

    }

    inner class EmptyMovieTrailersViewHolder(private val itemEmptyMovieTrailerBinding: ItemEmptyMovieTrailerBinding) :
        BaseViewHolder(itemEmptyMovieTrailerBinding.root), MovieTrailerEmptyItemListener {

        override fun onBind(position: Int) {
            itemEmptyMovieTrailerBinding.emptyMovieTrailer = MovieTrailerEmptyItemViewModel(this)
            itemEmptyMovieTrailerBinding.executePendingBindings()
        }

        override fun onRetryClick() {
            movieTrailersAdapterListener.onMovieTrailersRetry()
        }

    }
}