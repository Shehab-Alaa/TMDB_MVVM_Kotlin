package com.example.tmdbcleanarchitecture.data.model.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tmdbcleanarchitecture.utils.AppConstants
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*@Entity(tableName = AppConstants.DATABASE_NAME)
class Movie(

    @SerializedName("popularity")
    val popularity: Double?  ,

    @SerializedName("vote_count")
    val voteCount: Int?  ,

    @SerializedName("video")
    val video: Boolean?  ,

    @SerializedName("poster_path")
    val posterPath: String?  ,

    @SerializedName("id")
    @PrimaryKey
    @NonNull
    val id: Int  ,

    @SerializedName("adult")
    val adult: Boolean? ,

    @SerializedName("backdrop_path")
    val backdropPath: String? ,

    @SerializedName("original_language")
    val originalLanguage: String? ,

    @SerializedName("original_title")
    val originalTitle: String? ,

    @SerializedName("title")
    val title: String? ,

    @SerializedName("vote_average")
    val voteAverage: Double? ,

    @SerializedName("overview")
    val overview: String? ,

    @SerializedName("release_date")
    val releaseDate: String?
)*/

@Entity(tableName = AppConstants.DATABASE_NAME)
class Movie : Serializable {

    @SerializedName("popularity")
    var popularity: Double? = null

    @SerializedName("vote_count")
    var voteCount: Int? = null

    @SerializedName("video")
    var video: Boolean? = null

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("id")
    @PrimaryKey
    @NonNull
    var id: Int = 0

    @SerializedName("adult")
    var adult: Boolean? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("original_language")
    var originalLanguage: String? = null

    @SerializedName("original_title")
    var originalTitle: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("vote_average")
    var voteAverage: Double? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("release_date")
    var releaseDate: String? = null
}

