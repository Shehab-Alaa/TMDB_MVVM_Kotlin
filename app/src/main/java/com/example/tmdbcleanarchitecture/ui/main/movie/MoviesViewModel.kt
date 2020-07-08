package com.example.tmdbcleanarchitecture.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagedList
import com.example.tmdbcleanarchitecture.data.DataManager
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.ui.base.BaseViewModel
import com.example.tmdbcleanarchitecture.utils.AppConstants

class MoviesViewModel(dataManager: DataManager , saveStateHandle : SavedStateHandle) : BaseViewModel(dataManager,saveStateHandle) {

    private val category : String = getSaveStateHandle().get(AppConstants.SELECTED_CATEGORY)!!
    private var moviesPagesList : LiveData<PagedList<Movie>> = MutableLiveData()

    init {
        fetchMoviesPagedList()
    }

    private fun fetchMoviesPagedList(){
        moviesPagesList = getDataManager().getApiRepository().fetchLiveMoviesPagedList(category)
    }

    val moviesPagedListLiveData : LiveData<PagedList<Movie>> get() = moviesPagesList
}