package com.example.tmdbcleanarchitecture.ui.main.movie

import android.view.View
import androidx.databinding.ObservableField
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.ui.base.BaseItemListener

class MovieItemViewModel(private val movie: Movie,
                         private val movieItemClickListener: MovieItemClickListener) {

    val moviePoster: ObservableField<String?> = ObservableField<String?>(movie.posterPath)
    val movieTitle: ObservableField<String?> = ObservableField<String?>(movie.title)

    fun onItemClick(view: View) {
        movieItemClickListener.onItemClick(view, movie)
    }

    interface MovieItemClickListener : BaseItemListener<Movie> {
        // to be implemented by the adapter.
    }

}