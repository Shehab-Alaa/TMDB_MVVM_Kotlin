package com.example.tmdbcleanarchitecture.ui.main.movie_details.similar_movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.databinding.ItemEmptyMovieBinding
import com.example.tmdbcleanarchitecture.databinding.ItemMovieBinding
import com.example.tmdbcleanarchitecture.ui.base.BaseEmptyItemListener
import com.example.tmdbcleanarchitecture.ui.base.BaseItemListener
import com.example.tmdbcleanarchitecture.ui.base.BaseRecyclerViewAdapter
import com.example.tmdbcleanarchitecture.ui.base.BaseViewHolder
import com.example.tmdbcleanarchitecture.ui.main.movie.MovieEmptyItemViewModel
import com.example.tmdbcleanarchitecture.ui.main.movie.MovieItemViewModel
import com.example.tmdbcleanarchitecture.utils.AppConstants

class SimilarMoviesAdapter(moviesItems : MutableList<Movie>, private val moviesAdapterListener: MoviesAdapterListener) : BaseRecyclerViewAdapter<Movie>(moviesItems) {

    override fun getItemViewType(position: Int): Int {
        return if (getItems().isNotEmpty()) AppConstants.VIEW_TYPE_NORMAL else AppConstants.VIEW_TYPE_EMPTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            AppConstants.VIEW_TYPE_NORMAL -> MoviesViewHolder(
                ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> EmptyViewHolder(ItemEmptyMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    interface MoviesAdapterListener : BaseItemListener<Movie>, BaseEmptyItemListener

    inner class MoviesViewHolder(private val itemMovieBinding: ItemMovieBinding) : BaseViewHolder(itemMovieBinding.root), MovieItemViewModel.MovieItemClickListener{

        override fun onBind(position: Int) {
            val movie: Movie = getItems()[position]
            itemMovieBinding.movieItemViewModel = MovieItemViewModel(movie, this)
            itemMovieBinding.executePendingBindings()
        }

        override fun onItemClick(view: View, item: Movie) {
            moviesAdapterListener.onItemClick(view, item)
        }
    }

    inner class EmptyViewHolder(private val itemEmptyMovieBinding: ItemEmptyMovieBinding) : BaseViewHolder(itemEmptyMovieBinding.root), MovieEmptyItemViewModel.MovieEmptyItemListener {

        override fun onBind(position: Int) {
            itemEmptyMovieBinding.emptyItemViewModel = MovieEmptyItemViewModel(this)
            itemEmptyMovieBinding.executePendingBindings()
        }

        override fun onRetryClick() {
            moviesAdapterListener.onRetryClick()
        }

    }

}

