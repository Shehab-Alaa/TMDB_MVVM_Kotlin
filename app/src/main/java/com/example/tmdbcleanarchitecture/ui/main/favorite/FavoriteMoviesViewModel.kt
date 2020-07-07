package com.example.tmdbcleanarchitecture.ui.main.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.tmdbcleanarchitecture.data.DataManager
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.ui.base.BaseViewModel

class FavoriteMoviesViewModel(dataManager: DataManager , saveStateHandle : SavedStateHandle) : BaseViewModel(dataManager , saveStateHandle) {

    val favoriteMoviesList : LiveData<MutableList<Movie>> by lazy{
        getDataManager().getDatabaseRepository().getLiveFavoriteMovies()
    }

}
