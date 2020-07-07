package com.example.tmdbcleanarchitecture.ui.main.movie_details.movie_trailers

import androidx.databinding.ObservableField
import com.example.tmdbcleanarchitecture.data.model.details.MovieTrailer

class MovieTrailerItemViewModel(private val movieTrailer: MovieTrailer, private val movieTrailerClickListener: MovieTrailerClickListener) {

    var movieTrailerThumbnails: ObservableField<String?> = ObservableField(movieTrailer.key)

    fun onItemClick() {
        movieTrailerClickListener.onMovieTrailerClick(movieTrailer)
    }

    interface MovieTrailerClickListener {
        fun onMovieTrailerClick(movieTrailer: MovieTrailer?)
    }

}