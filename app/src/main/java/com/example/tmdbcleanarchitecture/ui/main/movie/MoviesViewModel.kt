package com.example.tmdbcleanarchitecture.ui.main.movie

import android.graphics.pdf.PdfDocument
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.tmdbcleanarchitecture.data.DataManager
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.ui.base.BaseViewModel
import com.example.tmdbcleanarchitecture.utils.AppConstants
import com.google.android.material.chip.ChipDrawable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class MoviesViewModel(dataManager: DataManager , saveStateHandle : SavedStateHandle) : BaseViewModel(dataManager,saveStateHandle) {

    private var category : String = getSaveStateHandle().get(AppConstants.SELECTED_CATEGORY)!!

    val moviesPagedList : LiveData<PagedList<Movie>> by lazy{
          getDataManager().getApiRepository().fetchLiveMoviesPagedList(category)
    }

}