package com.example.tmdbcleanarchitecture.ui.main.movie

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.tmdbcleanarchitecture.BR
import com.example.tmdbcleanarchitecture.R
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.databinding.FragmentMoviesBinding
import com.example.tmdbcleanarchitecture.ui.base.BaseFragment
import com.example.tmdbcleanarchitecture.utils.AppConstants
import com.example.tmdbcleanarchitecture.utils.GridLayoutManagerWrapper
import com.example.tmdbcleanarchitecture.utils.GridSpacingItemDecorationUtils
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf


class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesViewModel>() , MoviesAdapter.MoviesAdapterListener {

    private lateinit var moviesViewModel : MoviesViewModel
    private val moviesAdapter: MoviesAdapter =  MoviesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       val args = MoviesFragmentArgs.fromBundle(requireArguments())
       moviesViewModel = getViewModel{ parametersOf(SavedStateHandle(mapOf(AppConstants.SELECTED_CATEGORY to args.categoryType))) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkScreenOrientation()

        getViewDataBinding().mainSwiperefresh.setOnRefreshListener {
            moviesAdapter.notifyDataSetChanged()
            getViewDataBinding().mainSwiperefresh.isRefreshing = false
        }

    }

    private fun checkScreenOrientation() {
        val orientation = this.resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // portrait mode
            initMoviesRecyclerView(2, 25)
        } else {
            // landscape mode
            initMoviesRecyclerView(4, 10)
        }
    }

    private fun initMoviesRecyclerView(spanCount: Int, spacing: Int) {
        val gridLayoutManager = GridLayoutManagerWrapper(getMContext(), spanCount)
        getViewDataBinding().moviesRv.layoutManager = gridLayoutManager
        getViewDataBinding().moviesRv.setHasFixedSize(true)
        // set Animation to all children (items) of this Layout
        val animID: Int = R.anim.layout_animation_fall_down
        val animation = AnimationUtils.loadLayoutAnimation(context, animID)
        getViewDataBinding().moviesRv.layoutAnimation = animation
        // equal spaces between grid items
        val includeEdge = true
        val gridSpacingItemDecorationUtils = GridSpacingItemDecorationUtils(spanCount , spacing , includeEdge)
        getViewDataBinding().moviesRv.addItemDecoration(gridSpacingItemDecorationUtils)
        getViewDataBinding().moviesRv.adapter = moviesAdapter
    }

    override val layoutId: Int
        get() = R.layout.fragment_movies
    override val bindingVariable: Int
        get() = BR.moviesViewModel

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onItemClick(view: View, item: Movie) {
        view.transitionName = item.posterPath
        val extras = FragmentNavigatorExtras(view to item.posterPath.toString())
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(item)
        getNavController().navigate(action,extras)
    }

    override fun onRetryClick() {
        getViewModel().fetchMoviesPagedList()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    override fun getViewModel(): MoviesViewModel {
        return moviesViewModel
    }


}