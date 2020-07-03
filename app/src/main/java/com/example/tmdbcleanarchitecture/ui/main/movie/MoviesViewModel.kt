package com.example.tmdbcleanarchitecture.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.tmdbcleanarchitecture.data.DataManager
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.ui.base.BaseViewModel

class MoviesViewModel(dataManager: DataManager) : BaseViewModel(dataManager) {

    lateinit var category : String

    val moviesPagedList : LiveData<PagedList<Movie>> by lazy{
          getDataManager().getApiRepository().fetchLiveMoviesPagedList(category)
    }


}