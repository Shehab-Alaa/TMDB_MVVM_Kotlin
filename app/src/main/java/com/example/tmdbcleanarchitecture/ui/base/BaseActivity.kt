package com.example.tmdbcleanarchitecture.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.SavedStateViewModelFactory
import com.example.tmdbcleanarchitecture.di.ViewModelsFactory

abstract class BaseActivity<T : ViewDataBinding , V : BaseViewModel> : AppCompatActivity() {

    private lateinit var mViewDataBinding: T
    private lateinit var mViewModel: V
    private lateinit var mViewModelsFactory: ViewModelsFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModelsFactory = initViewModelsFactory()
        mViewModel = initViewModel()
        performDataBinding()
    }

    private fun performDataBinding() {
        // Binding Class
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding.lifecycleOwner = this
    }

    abstract fun getLayoutId(): Int
    abstract fun initViewModelsFactory() : ViewModelsFactory
    abstract fun initViewModel(): V


    fun getViewModel(): V {
        return mViewModel
    }

    fun getViewDataBinding(): T {
        return mViewDataBinding
    }

    fun getViewModelFactory() : ViewModelsFactory{
        return mViewModelsFactory
    }

}