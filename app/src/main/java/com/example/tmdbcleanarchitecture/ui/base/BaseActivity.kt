package com.example.tmdbcleanarchitecture.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding , V : BaseViewModel> : AppCompatActivity() {

    private lateinit var mViewDataBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding() {
        // Binding Class
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding.lifecycleOwner = this
    }

    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    fun getViewDataBinding(): T {
        return mViewDataBinding
    }

}