package com.example.tmdbcleanarchitecture.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding , V : BaseViewModel> : AppCompatActivity() {

    lateinit var mViewDataBinding: T
    lateinit var mViewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = initViewModel()
        performDataBinding()
    }

    private fun performDataBinding() {
        // Binding Class
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding.lifecycleOwner = this
    }

    abstract fun getLayoutId(): Int

    abstract fun initViewModel(): V


    fun getViewModel(): V {
        return mViewModel
    }

    fun getViewDataBinding(): T {
        return mViewDataBinding
    }

}