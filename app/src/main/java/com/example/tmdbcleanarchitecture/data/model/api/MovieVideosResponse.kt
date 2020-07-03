package com.example.tmdbcleanarchitecture.data.model.api

import com.example.tmdbcleanarchitecture.data.model.MovieTrailer
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieVideosResponse (
    @SerializedName("id")
    @Expose
    val id: Int ,
    @SerializedName("results")
    @Expose
    val movieTrailers: List<MovieTrailer> =
        ArrayList<MovieTrailer>()
)