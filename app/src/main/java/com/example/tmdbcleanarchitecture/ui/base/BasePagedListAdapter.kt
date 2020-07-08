package com.example.tmdbcleanarchitecture.ui.base

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.tmdbcleanarchitecture.data.model.db.Movie

abstract class BasePagedListAdapter : PagedListAdapter<Movie,BaseViewHolder>(MovieDiffCallback()) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return if (currentList != null && currentList!!.size >  0) currentList!!.size else 1
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