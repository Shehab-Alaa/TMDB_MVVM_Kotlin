package com.example.tmdbcleanarchitecture.ui.main.movie

import com.example.tmdbcleanarchitecture.ui.base.BaseEmptyItemListener


class MovieEmptyItemViewModel(private val movieEmptyItemListener: MovieEmptyItemListener) {

    fun onRetryClick() {
        movieEmptyItemListener.onRetryClick()
    }

    interface MovieEmptyItemListener : BaseEmptyItemListener

}