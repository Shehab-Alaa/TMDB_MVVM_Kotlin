package com.example.tmdbcleanarchitecture.ui.main.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdbcleanarchitecture.data.DataManager
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.ui.base.BaseViewModel

class FavoriteMoviesViewModel(dataManager: DataManager) : BaseViewModel(dataManager) {

    val favoriteMoviesList : LiveData<MutableList<Movie>> by lazy{
        getDataManager().getDatabaseRepository().getLiveFavoriteMovies()
    }

}
