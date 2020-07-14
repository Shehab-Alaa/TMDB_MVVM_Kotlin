package com.example.tmdbcleanarchitecture.ui.main.movie

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.tmdbcleanarchitecture.BR
import com.example.tmdbcleanarchitecture.R
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.databinding.FragmentMoviesBinding
import com.example.tmdbcleanarchitecture.di.ViewModelsFactory
import com.example.tmdbcleanarchitecture.ui.base.BaseFragment
import com.example.tmdbcleanarchitecture.utils.AppConstants
import com.example.tmdbcleanarchitecture.utils.GridLayoutManagerWrapper
import com.example.tmdbcleanarchitecture.utils.GridSpacingItemDecorationUtils
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesViewModel>() , MoviesAdapter.MoviesAdapterListener {

    private val viewModelsFactory : ViewModelsFactory by inject { parametersOf(this) }
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moviesAdapter = MoviesAdapter(this)
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
        getViewDataBinding().moviesRv.addItemDecoration(GridSpacingItemDecorationUtils(spanCount, spacing, includeEdge))
        getViewDataBinding().moviesRv.adapter = moviesAdapter
    }

    override fun initViewModel(): MoviesViewModel {
        val args : MoviesFragmentArgs = MoviesFragmentArgs.fromBundle(requireArguments())
        return viewModelsFactory.create(AppConstants.VIEW_MODEL_KEY , MoviesViewModel::class.java , SavedStateHandle(
            mapOf(AppConstants.SELECTED_CATEGORY to args.categoryType))
        )
    }

    override val layoutId: Int
        get() = R.layout.fragment_movies
    override val bindingVariable: Int
        get() = BR.moviesViewModel

    override fun onItemClick(view: View?, item: Movie) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(item)
        getNavController().navigate(action)
    }

    override fun onRetryClick() {
        getViewModel().fetchMoviesPagedList()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

}