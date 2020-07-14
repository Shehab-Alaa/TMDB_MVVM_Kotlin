package com.example.tmdbcleanarchitecture.ui.main.favorite

import android.view.View
import androidx.databinding.ObservableField
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.ui.base.BaseItemListener


class FavoriteItemViewModel(private val movie: Movie, private val favoriteMovieItemClickListener: FavoriteMovieItemClickListener) {

    val moviePoster: ObservableField<String> = ObservableField(movie.posterPath.toString())
    val movieTitle: ObservableField<String> = ObservableField(movie.originalTitle.toString())

    fun onItemClick(view: View) {
        favoriteMovieItemClickListener.onItemClick(view, movie)
    }

    interface FavoriteMovieItemClickListener : BaseItemListener<Movie> {
        // to be implemented by the adapter.
    }

}