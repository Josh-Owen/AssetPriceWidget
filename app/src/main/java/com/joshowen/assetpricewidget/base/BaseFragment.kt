package com.joshowen.assetpricewidget.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {

    //region Variables
    private var _binding: Binding? = null
    private val binding get() = _binding!!
    private val compositeDisposable = CompositeDisposable()
    //endregion

    //region Fragment Life-Cycle
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            initArgs(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateBinding(inflater)
        initViews()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    override fun onResume() {
        super.onResume()
        observeViewModel()
    }

    //endregion

    //region Memory Management

    fun Disposable.autoDispose() {
        compositeDisposable.add(this)
    }

    //endregion

    //region Fragment Utility

    abstract fun inflateBinding(layoutInflater: LayoutInflater): Binding

    abstract fun observeViewModel()

    open fun initArgs(arguments: Bundle) {}

    open fun initViews() {}

    //endregion

}

