package com.example.tmdbcleanarchitecture.utils

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridLayoutManagerWrapper(context: Context , spanCount : Int) : GridLayoutManager(context , spanCount) {

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException) {
            Log.e("Here", "RecyclerView Error")
        }
    }
}