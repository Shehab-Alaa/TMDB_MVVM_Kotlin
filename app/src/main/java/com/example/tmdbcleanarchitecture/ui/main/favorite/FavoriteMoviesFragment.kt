package com.example.tmdbcleanarchitecture.ui.main.favorite

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbcleanarchitecture.BR
import com.example.tmdbcleanarchitecture.R
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.databinding.FragmentFavoriteMoviesBinding
import com.example.tmdbcleanarchitecture.ui.base.BaseFragment
import com.example.tmdbcleanarchitecture.utils.AppConstants
import com.example.tmdbcleanarchitecture.utils.GridSpacingItemDecorationUtils
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class FavoriteMoviesFragment : BaseFragment<FragmentFavoriteMoviesBinding, FavoriteMoviesViewModel>(),
    FavoriteMoviesAdapter.FavoritesAdapterListener {

    private val favoriteMoviesViewModel : FavoriteMoviesViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf()))}
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

    override val layoutId: Int
        get() = R.layout.fragment_favorite_movies
    override val bindingVariable: Int
        get() = BR.favoriteMoviesViewModel

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onItemClick(view: View, item: Movie) {
        view.transitionName = item.posterPath
        val extras = FragmentNavigatorExtras(view to item.posterPath.toString())
        val action = FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToMovieDetailsFragment(item)
        getNavController().navigate(action,extras)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    override fun getViewModel(): FavoriteMoviesViewModel {
        return favoriteMoviesViewModel
    }

}