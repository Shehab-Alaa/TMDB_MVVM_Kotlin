package com.example.tmdbcleanarchitecture.data.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.tmdbcleanarchitecture.data.model.Result
import com.example.tmdbcleanarchitecture.data.model.api.DataResponse
import com.example.tmdbcleanarchitecture.data.model.api.MovieReviewResponse
import com.example.tmdbcleanarchitecture.data.model.api.MovieVideosResponse
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.data.model.details.MovieDetails

interface ApiDataSource {
    fun fetchLiveMoviesPagedList (category: String) : LiveData<PagedList<Movie>>
    suspend fun fetchLiveMovieDetailsData(movieID : Int) : Result<MovieDetails>
    suspend fun fetchLiveSimilarMoviesList(movieID: Int) : Result<DataResponse>
    suspend fun fetchLiveMovieReviewsList(movieID: Int) : Result<MovieReviewResponse>
    suspend fun fetchLiveMovieTrailersList(movieID: Int) : Result<MovieVideosResponse>
}