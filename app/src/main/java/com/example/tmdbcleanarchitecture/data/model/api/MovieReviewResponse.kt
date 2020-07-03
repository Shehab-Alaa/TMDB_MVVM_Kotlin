package com.example.tmdbcleanarchitecture.data.model.api

import com.example.tmdbcleanarchitecture.data.model.MovieReview
import com.google.gson.annotations.SerializedName

data class MovieReviewResponse(
    @SerializedName("id")
    val id: Int ,
    @SerializedName("page")
    val page: Int ,
    @SerializedName("results")
    val movieReviews: List<MovieReview> ,
    @SerializedName("total_pages")
    val totalPages: Int ,
    @SerializedName("total_results")
    val totalResults: Int
)
