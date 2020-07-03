package com.example.tmdbcleanarchitecture.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BasicMovieDetails  (
    @SerializedName("id")
    @Expose
    val id: Int ,

    @SerializedName("name")
    @Expose
    val name: String ,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String ,

    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String

)