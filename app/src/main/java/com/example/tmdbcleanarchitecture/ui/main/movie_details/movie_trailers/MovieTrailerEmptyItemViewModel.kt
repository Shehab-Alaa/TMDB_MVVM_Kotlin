package com.example.tmdbcleanarchitecture.ui.main.movie_details.movie_trailers

import com.example.tmdbcleanarchitecture.ui.base.BaseEmptyItemListener

class MovieTrailerEmptyItemViewModel(private val movieTrailerEmptyItemListener: MovieTrailerEmptyItemListener) {

    fun onRetryClick() {
        movieTrailerEmptyItemListener.onRetryClick()
    }

    interface MovieTrailerEmptyItemListener : BaseEmptyItemListener

}