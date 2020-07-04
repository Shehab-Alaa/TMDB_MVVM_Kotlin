package com.example.tmdbcleanarchitecture.ui.main.movie_details

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbcleanarchitecture.BR
import com.example.tmdbcleanarchitecture.R
import com.example.tmdbcleanarchitecture.data.model.MovieTrailer
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.databinding.FragmentMovieDetailsBinding
import com.example.tmdbcleanarchitecture.di.ViewModelsFactory
import com.example.tmdbcleanarchitecture.ui.base.BaseFragment
import com.example.tmdbcleanarchitecture.ui.main.movie_details.movie_reviews.MovieReviewsAdapter
import com.example.tmdbcleanarchitecture.ui.main.movie_details.movie_trailers.MovieTrailersAdapter
import com.example.tmdbcleanarchitecture.ui.main.movie_details.similar_movies.SimilarMoviesAdapter
import org.koin.android.ext.android.inject

class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel>(),
    SimilarMoviesAdapter.MoviesAdapterListener, MovieTrailersAdapter.MovieTrailersAdapterListener {

    private val viewModelsFactory : ViewModelsFactory by inject()
    private lateinit var movie : Movie
    private val similarMoviesAdapter = SimilarMoviesAdapter(mutableListOf() , this)
    private val movieReviewsAdapter = MovieReviewsAdapter(mutableListOf())
    private val movieTrailersAdapter  = MovieTrailersAdapter(mutableListOf() , this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = MovieDetailsFragmentArgs.fromBundle(requireArguments())
        movie = args.selectedMovie
        getViewModel().movie = movie
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initToolbar()
        // Check For Favorite View Model
        getViewDataBinding().collapsingToolbar.isTitleEnabled = true
        getViewDataBinding().collapsingToolbar.title = movie.title

        return gerMRootView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(getViewDataBinding().rvSimilarMovies , similarMoviesAdapter , RecyclerView.HORIZONTAL)
        initRecyclerView(getViewDataBinding().rvMovieTrailers , movieTrailersAdapter , RecyclerView.HORIZONTAL)
        initRecyclerView(getViewDataBinding().rvMovieReviews , movieReviewsAdapter , RecyclerView.VERTICAL)
    }

    override fun initViewModel(): MovieDetailsViewModel {
        return ViewModelProvider(this,viewModelsFactory).get(MovieDetailsViewModel::class.java)
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

    override fun onItemClick(view: View?, item: Movie?) {
        TODO("Not yet implemented")
    }

    override fun onRetryClick() {
        TODO("Not yet implemented")
    }

    override fun onMovieTrailersRetry() {
        TODO("Not yet implemented")
    }

    override fun onMovieTrailerClick(movieTrailer: MovieTrailer?) {
        TODO("Not yet implemented")
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
                Log.i("Here" , "Collapsing Toolbar")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

}