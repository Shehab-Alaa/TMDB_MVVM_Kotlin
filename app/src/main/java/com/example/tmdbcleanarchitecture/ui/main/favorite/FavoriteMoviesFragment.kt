package com.example.tmdbcleanarchitecture.ui.main.favorite

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbcleanarchitecture.BR
import com.example.tmdbcleanarchitecture.R
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.di.ViewModelsFactory
import com.example.tmdbcleanarchitecture.databinding.FragmentFavoriteMoviesBinding
import com.example.tmdbcleanarchitecture.ui.base.BaseFragment
import com.example.tmdbcleanarchitecture.utils.GridSpacingItemDecorationUtils
import org.koin.android.ext.android.inject


class FavoriteMoviesFragment : BaseFragment<FragmentFavoriteMoviesBinding, FavoriteMoviesViewModel>(),
    FavoriteMoviesAdapter.FavoritesAdapterListener {

    private val viewModelsFactory : ViewModelsFactory by inject()
    private val favoriteMoviesAdapter = FavoriteMoviesAdapter(mutableListOf() , this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkScreenOrientation()
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
        val gridLayoutManager = GridLayoutManager(context, spanCount)
        getViewDataBinding().favoriteMoviesRv.layoutManager = gridLayoutManager
        getViewDataBinding().favoriteMoviesRv.setHasFixedSize(true)
        // set Animation to all children (items) of this Layout
        val animID: Int = R.anim.layout_animation_fall_down
        val animation = AnimationUtils.loadLayoutAnimation(context, animID)
        getViewDataBinding().favoriteMoviesRv.layoutAnimation = animation
        // equal spaces between grid items
        val includeEdge = true
        getViewDataBinding().favoriteMoviesRv.addItemDecoration(GridSpacingItemDecorationUtils(spanCount, spacing, includeEdge))
        getViewDataBinding().favoriteMoviesRv.adapter = favoriteMoviesAdapter
    }

    override fun initViewModel(): FavoriteMoviesViewModel {
        return ViewModelProvider(this,viewModelsFactory).get(FavoriteMoviesViewModel::class.java)
    }

    override val layoutId: Int
        get() = R.layout.fragment_favorite_movies
    override val bindingVariable: Int
        get() = BR.favoriteMoviesViewModel

    override fun onItemClick(view: View?, item: Movie) {
        val action = FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToMovieDetailsFragment(item)
        getNavController().navigate(action)
    }

}