package com.example.tmdbcleanarchitecture.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>(private val items: MutableList<T>) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun clearItems() {
        items.clear()
    }

    open fun addItems(items: List<T>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (items.size > 0) items.size else 1
    } // 1 for Binding Empty Item;

    fun getItems(): MutableList<T> {
        return items
    }

}
