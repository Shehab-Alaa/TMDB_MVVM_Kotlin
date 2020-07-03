package com.example.tmdbcleanarchitecture.utils

import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.data.remote.network.ApiClient
import com.example.tmdbcleanarchitecture.ui.main.movie.MoviesAdapter
import org.koin.core.parameter.parametersOf

object BindingAdaptersUtils {

   /* @BindingAdapter("android:ratingBar")
    fun setRatingBarView(movieRate: RatingBar, movieVoteAverage: Double) {
        val rating = (movieVoteAverage * 5 / 9).toFloat()
        movieRate.numStars = 5
        movieRate.stepSize = 0.1f
        movieRate.rating = rating
        movieRate.setIsIndicator(true)
    }

    @BindingAdapter("android:trailerImage")
    fun setMovieTrailerImage(
        movieTrailerThumbnail: ImageView,
        trailerKey: String
    ) {
        PicassoCache
            .getPicassoInstance(movieTrailerThumbnail.context)
            .load(YoutubeClient.YOUTUBE_VIDEO_THUMBNAIL.toString() + trailerKey + "/0.jpg")
            .into(movieTrailerThumbnail, object : Callback() {
                fun onSuccess() {}
                fun onError(e: Exception?) {}
            })
    }


    @BindingAdapter("android:categoriesText")
    fun setCategoriesTextViewData(
        movieCategories: TextView,
        categories: List<Category>?
    ) {
        var categoriesHolder = ""
        if (categories != null) {
            for (category in categories) categoriesHolder += category.getName()
                .toString() + ". "
        }
        movieCategories.text = categoriesHolder
    }

    @BindingAdapter("android:statusImage")
    fun setMovieStatusImageView(
        movieStatusImage: ImageView,
        movieStatus: String?
    ) {
        if (movieStatus != null) {
            if (movieStatus == "Released") {
                movieStatusImage.setImageDrawable(
                    movieStatusImage.resources.getDrawable(R.drawable.ic_released)
                )
            } else {
                movieStatusImage.setImageDrawable(
                    movieStatusImage.resources.getDrawable(R.drawable.ic_un_released)
                )
            }
        }
    }

    @BindingAdapter("android:statusText")
    fun setMovieStatusTextView(
        movieStatusText: TextView,
        movieStatus: String?
    ) {
        if (movieStatus != null) {
            if (movieStatus == "Released") {
                movieStatusText.text = movieStatus
            } else {
                movieStatusText.text = movieStatus
            }
        }
    }

    @BindingAdapter("android:isAdultMovie")
    fun setMovieAdultText(adultMovieText: TextView, isAdult: Boolean) {
        if (isAdult) adultMovieText.text = "Yes" else adultMovieText.text = "No"
    }
*/

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("android:adapterList")
    fun setRecyclerViewData(recyclerView: RecyclerView , moviesList: LiveData<PagedList<Movie>>?){
        moviesList?.let {
            (recyclerView.adapter as? MoviesAdapter)?.apply {
                submitList(moviesList.value)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("android:posterSrc")
    fun setMoviePosterSrc(posterImage: ImageView , posterPath : String?){
        val moviePosterURL = ApiClient.POSTER_BASE_URL + posterPath
        Glide.with(posterImage.context)
            .load(moviePosterURL)
            .into(posterImage);
    }
}