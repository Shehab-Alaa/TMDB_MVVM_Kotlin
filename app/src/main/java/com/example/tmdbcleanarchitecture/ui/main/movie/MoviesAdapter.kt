package com.example.tmdbcleanarchitecture.ui.main.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.tmdbcleanarchitecture.BR
import com.example.tmdbcleanarchitecture.R
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.databinding.ItemEmptyMovieBinding
import com.example.tmdbcleanarchitecture.databinding.ItemMovieBinding
import com.example.tmdbcleanarchitecture.ui.base.BaseEmptyItemListener
import com.example.tmdbcleanarchitecture.ui.base.BaseItemListener
import com.example.tmdbcleanarchitecture.ui.base.BasePagedListAdapter
import com.example.tmdbcleanarchitecture.ui.base.BaseViewHolder
import com.example.tmdbcleanarchitecture.utils.AppConstants

class MoviesAdapter(val itemListener: MoviesAdapterListener) : BasePagedListAdapter() {

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder{
        return when(viewType){
            AppConstants.VIEW_TYPE_NORMAL -> { MoviesViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context) , R.layout.item_movie , parent , false)) }
           else -> { EmptyMovieViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context) , R.layout.item_empty_movie , parent , false)) }
       }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList != null && currentList!!.isNotEmpty()) AppConstants.VIEW_TYPE_NORMAL else AppConstants.VIEW_TYPE_EMPTY
    }

    interface MoviesAdapterListener : BaseItemListener<Movie> , BaseEmptyItemListener

    inner class MoviesViewHolder(private var itemMovieBinding: ItemMovieBinding) : BaseViewHolder(itemMovieBinding.root) , MovieItemViewModel.MovieItemClickListener{

        private lateinit var movieItemViewModel: MovieItemViewModel

        override fun onBind(position: Int) {
            movieItemViewModel = getItem(position)?.let { MovieItemViewModel(it, this) }!!
            itemMovieBinding.setVariable(BR.movieItemViewModel , movieItemViewModel)
            itemMovieBinding.executePendingBindings()
        }

        override fun onItemClick(view: View, item: Movie) {
            itemListener.onItemClick(view,item)
        }

    }

    inner class EmptyMovieViewHolder(private var emptyItemMovieBinding: ItemEmptyMovieBinding) : BaseViewHolder(emptyItemMovieBinding.root ) , MovieEmptyItemViewModel.MovieEmptyItemListener {

        override fun onBind(position: Int) {
            emptyItemMovieBinding.emptyItemViewModel = MovieEmptyItemViewModel(this)
            emptyItemMovieBinding.executePendingBindings()
        }

        override fun onRetryClick() {
            itemListener.onRetryClick()
        }
    }


}