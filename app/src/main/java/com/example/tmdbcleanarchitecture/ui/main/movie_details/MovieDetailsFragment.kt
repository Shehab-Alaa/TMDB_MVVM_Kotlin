package com.example.tmdbcleanarchitecture.ui.main.movie_details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.example.tmdbcleanarchitecture.BR
import com.example.tmdbcleanarchitecture.R
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.data.model.details.MovieTrailer
import com.example.tmdbcleanarchitecture.databinding.FragmentMovieDetailsBinding
import com.example.tmdbcleanarchitecture.ui.base.BaseFragment
import com.example.tmdbcleanarchitecture.ui.main.movie_details.movie_reviews.MovieReviewsAdapter
import com.example.tmdbcleanarchitecture.ui.main.movie_details.movie_trailers.MovieTrailersAdapter
import com.example.tmdbcleanarchitecture.ui.main.movie_details.similar_movies.SimilarMoviesAdapter
import com.example.tmdbcleanarchitecture.utils.AppConstants
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf


class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel>(),
    SimilarMoviesAdapter.MoviesAdapterListener, MovieTrailersAdapter.MovieTrailersAdapterListener {

    private lateinit var movieDetailsViewModel : MovieDetailsViewModel
    private val similarMoviesAdapter = SimilarMoviesAdapter(mutableListOf() , this)
    private val movieReviewsAdapter = MovieReviewsAdapter(mutableListOf())
    private val movieTrailersAdapter  = MovieTrailersAdapter(mutableListOf() , this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = MovieDetailsFragmentArgs.fromBundle(requireArguments())
        movieDetailsViewModel =  getViewModel{ parametersOf(SavedStateHandle(mapOf(AppConstants.SELECTED_MOVIE to args.selectedMovie)))}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initToolbar()
        return gerMRootView()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLayoutAnimation()

        initRecyclerView(getViewDataBinding().rvSimilarMovies , similarMoviesAdapter , RecyclerView.HORIZONTAL)
        initRecyclerView(getViewDataBinding().rvMovieTrailers , movieTrailersAdapter , RecyclerView.HORIZONTAL)
        initRecyclerView(getViewDataBinding().rvMovieReviews , movieReviewsAdapter , RecyclerView.VERTICAL)
    }

    override val layoutId: Int
        get() = R.layout.fragment_movie_details
    override val bindingVariable: Int
        get() = BR.movieDetailsViewModel

    private fun initRecyclerView(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>, orientation: Int) {
        recyclerView.layoutManager = LinearLayoutManager(context, orientation, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(view: View, item: Movie) {
        val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentSelf(item)
        getNavController().navigate(action)
    }

    override fun onRetryClick() {
        getViewModel().fetchSimilarMovies()
    }

    override fun onMovieTrailersRetry() {
        getViewModel().fetchMovieTrailers()
    }

    override fun onMovieTrailerClick(movieTrailer: MovieTrailer?) {
        openYoutubeApp(movieTrailer?.key)
    }

    private fun openYoutubeApp(videoId: String?) {
        val appIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(AppConstants.YOUTUBE_APP_LINK + videoId))
        val webIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(AppConstants.YOUTUBE_WEB_LINK + videoId))
        try {
            context?.startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            context?.startActivity(webIntent)
        }
    }

    private fun initToolbar() {
        val layout = getViewDataBinding().collapsingToolbar
        val toolbar = getViewDataBinding().toolbar
        val navController =
            activity?.let { Navigation.findNavController(it,R.id.nav_host_fragment) }
        val appBarConfiguration =
            navController?.graph?.let { AppBarConfiguration.Builder(it).build() }
        if (navController != null) {
            if (appBarConfiguration != null) {
                NavigationUI.setupWithNavController(layout, toolbar, navController, appBarConfiguration)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setLayoutAnimation() {
        getViewDataBinding().moviePoster.transitionName = getViewModel().movie.posterPath
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        val rightAnimationController: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_right)
        getViewDataBinding().movieDetailsLayout.layoutAnimation = rightAnimationController
        val bottomAnimationController: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom)
        getViewDataBinding().movieOverviewLayout.layoutAnimation = bottomAnimationController
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun getViewModel(): MovieDetailsViewModel {
        return movieDetailsViewModel
    }


}