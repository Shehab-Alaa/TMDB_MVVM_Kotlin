package com.example.tmdbcleanarchitecture.data.remote.network


import com.example.tmdbcleanarchitecture.data.model.MovieDetails
import com.example.tmdbcleanarchitecture.data.model.api.DataResponse
import com.example.tmdbcleanarchitecture.data.model.api.MovieReviewResponse
import com.example.tmdbcleanarchitecture.data.model.api.MovieVideosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(ApiEndPoint.END_POINT_TMDB + "{category}")
    suspend fun getMovies(
        @Path("category") category: String?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int
    ): DataResponse

    @GET(ApiEndPoint.END_POINT_TMDB + "{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ): MovieDetails

    @GET(ApiEndPoint.END_POINT_TMDB + "{movie_id}/videos")
    suspend fun getMovieTrailers(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ): MovieVideosResponse

    @GET(ApiEndPoint.END_POINT_TMDB + "{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int
    ): MovieReviewResponse

    @GET(ApiEndPoint.END_POINT_TMDB + "{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int
    ): DataResponse
}