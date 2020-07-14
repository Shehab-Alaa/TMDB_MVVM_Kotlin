package com.example.tmdbcleanarchitecture.ui.main.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.databinding.ItemEmptyFavoriteMovieBinding
import com.example.tmdbcleanarchitecture.databinding.ItemFavoriteMovieBinding
import com.example.tmdbcleanarchitecture.ui.base.BaseItemListener
import com.example.tmdbcleanarchitecture.ui.base.BaseRecyclerViewAdapter
import com.example.tmdbcleanarchitecture.ui.base.BaseViewHolder
import com.example.tmdbcleanarchitecture.ui.main.favorite.FavoriteItemViewModel.FavoriteMovieItemClickListener
import com.example.tmdbcleanarchitecture.utils.AppConstants

class FavoriteMoviesAdapter(items: MutableList<Movie>, private val favoritesAdapterListener: FavoritesAdapterListener) : BaseRecyclerViewAdapter<Movie>(items) {

    override fun getItemViewType(position: Int): Int {
        return if (getItems().isNotEmpty()) AppConstants.VIEW_TYPE_NORMAL else AppConstants.VIEW_TYPE_EMPTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            AppConstants.VIEW_TYPE_NORMAL -> MoviesViewHolder(ItemFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> EmptyViewHolder(
                ItemEmptyFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    interface FavoritesAdapterListener : BaseItemListener<Movie>

    override fun addItems(items: List<Movie>) {
        this.clearItems()
        super.addItems(items)
    }

    inner class MoviesViewHolder(private val itemFavoriteMovieBinding: ItemFavoriteMovieBinding) : BaseViewHolder(itemFavoriteMovieBinding.root), FavoriteMovieItemClickListener {

        override fun onBind(position: Int) {
            val movie: Movie = getItems()[position]
            itemFavoriteMovieBinding.viewModel = FavoriteItemViewModel(movie, this)
            itemFavoriteMovieBinding.executePendingBindings()
        }

        override fun onItemClick(view: View, item: Movie) {
            favoritesAdapterListener.onItemClick(view, item)
        }

    }

    inner class EmptyViewHolder(private val itemEmptyFavoriteMovieBinding: ItemEmptyFavoriteMovieBinding) : BaseViewHolder(itemEmptyFavoriteMovieBinding.root) {

        override fun onBind(position: Int) {
            itemEmptyFavoriteMovieBinding.favoriteEmptyItemViewModel = FavoriteEmptyItemViewModel()
            itemEmptyFavoriteMovieBinding.executePendingBindings()
        }
    }

}