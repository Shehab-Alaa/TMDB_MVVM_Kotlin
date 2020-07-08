package com.example.tmdbcleanarchitecture.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.tmdbcleanarchitecture.di.ViewModelsFactory

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {

    private lateinit var viewDataBinding: T
    private lateinit var viewModel: V
    private lateinit var viewModelsFactory: ViewModelsFactory
    private lateinit var navController: NavController
    private lateinit var mRootView : View
    private lateinit var mContext : Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelsFactory = initViewModelsFactory()
        viewModel = initViewModel()
    }

    abstract fun initViewModelsFactory() : ViewModelsFactory
    abstract fun initViewModel(): V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = viewDataBinding.root
        return mRootView
    }

    abstract val layoutId: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewDataBinding.setVariable(bindingVariable, viewModel)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()
    }

    abstract val bindingVariable: Int

    fun getViewDataBinding(): T {
        return viewDataBinding
    }

    fun getNavController(): NavController {
        return navController
    }

    fun getViewModel() : V {
        return viewModel
    }

    fun gerMRootView() : View{
        return mRootView
    }

    fun getViewModelFactory() : ViewModelsFactory{
        return viewModelsFactory
    }

    fun getMContext() : Context{
        return mContext
    }
}