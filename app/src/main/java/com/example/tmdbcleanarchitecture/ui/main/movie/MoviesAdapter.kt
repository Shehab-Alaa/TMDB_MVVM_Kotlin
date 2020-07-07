package com.example.tmdbcleanarchitecture.ui.main.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.tmdbcleanarchitecture.BR
import com.example.tmdbcleanarchitecture.R
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.databinding.ItemEmptyMovieBinding
import com.example.tmdbcleanarchitecture.databinding.ItemMovieBinding
import com.example.tmdbcleanarchitecture.ui.base.BaseEmptyItemListener
import com.example.tmdbcleanarchitecture.ui.base.BaseItemListener
import com.example.tmdbcleanarchitecture.ui.base.BaseViewHolder

class MoviesAdapter(val itemListener: MoviesAdapterListener) : PagedListAdapter<Movie,MoviesAdapter.MoviesViewHolder>(MovieDiffCallback()) {


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.onBind(position)
    }

   /* override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder{
        return when(viewType){
           AppConstants.VIEW_TYPE_NORMAL -> {
               Log.i("Here" , "Create Normal View Holder")
               MoviesViewHolder(
               DataBindingUtil.inflate(
               LayoutInflater.from(parent.context) , R.layout.item_movie , parent , false
           ))}
           else -> {
               Log.i("Here" , "Create Empty View Holder")
               EmptyMovieViewHolder(DataBindingUtil.inflate(
               LayoutInflater.from(parent.context) , R.layout.item_empty_movie , parent , false
           ))}
       }
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context) , R.layout.item_movie , parent , false
            ))
    }


   /* override fun getItemViewType(position: Int): Int {
        return if (currentList != null && !currentList!!.isEmpty()) {
            AppConstants.VIEW_TYPE_NORMAL
        } else {
            AppConstants.VIEW_TYPE_EMPTY
        }
    }*/


    interface MoviesAdapterListener : BaseItemListener<Movie> , BaseEmptyItemListener {

    }

    inner class MoviesViewHolder(private var itemMovieBinding: ItemMovieBinding) : BaseViewHolder(itemMovieBinding.root) , MovieItemViewModel.MovieItemClickListener{

        private lateinit var movieItemViewModel: MovieItemViewModel

        override fun onBind(position: Int) {
            movieItemViewModel = getItem(position)?.let { MovieItemViewModel(it, this) }!!
            itemMovieBinding.setVariable(BR.movieItemViewModel , movieItemViewModel)
            itemMovieBinding.executePendingBindings()
        }

        override fun onItemClick(view: View?, item: Movie) {
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

    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

}