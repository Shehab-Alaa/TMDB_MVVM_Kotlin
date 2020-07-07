package com.example.tmdbcleanarchitecture.data.model.details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDetails (

    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,

    @SerializedName("belongs_to_collection")
    @Expose
    var basicMovieDetails: BasicMovieDetails? = null,

    @SerializedName("budget")
    @Expose
    var budget: Int? = null,

    @SerializedName("genres")
    @Expose
    var categories: List<Category>,

    @SerializedName("homepage")
    @Expose
    var homepage: Any? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("imdb_id")
    @Expose
    var imdbId: String? = null,

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null,

    @SerializedName("overview")
    @Expose
    var overview: String? = null,

    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null,

    @SerializedName("revenue")
    @Expose
    var revenue: Int? = null,

    @SerializedName("runtime")
    @Expose
    var runtime: Int? = null,

    @SerializedName("spoken_languages")
    @Expose
    var spokenLanguages: List<SpokenLanguage>? = null,

    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("tagline")
    @Expose
    var tagline: String? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("video")
    @Expose
    var video: Boolean? = null,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null,

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null

)