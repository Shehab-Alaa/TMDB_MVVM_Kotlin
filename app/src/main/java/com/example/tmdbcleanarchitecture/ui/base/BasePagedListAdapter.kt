package com.example.tmdbcleanarchitecture.ui.base


import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.tmdbcleanarchitecture.data.model.db.Movie
import com.example.tmdbcleanarchitecture.utils.AppConstants

abstract class BasePagedListAdapter : PagedListAdapter<Movie,BaseViewHolder>(MovieDiffCallback()) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
         holder.onBind(position)
    }

    override fun getItemCount(): Int {
        Log.i("Here" , "Item Count " + super.getItemCount())
        return super.getItemCount()
    }

    /*override fun getItemCount(): Int {
        return if (currentList != null && currentList!!.size > 0) super.getItemCount() else 1
      }*/

    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }


}