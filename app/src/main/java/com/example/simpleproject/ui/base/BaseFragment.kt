package com.example.simpleproject.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.simpleproject.IViewModelState
import com.example.simpleproject.ui.root.RootActivity

abstract class BaseFragment<T: BaseViewModel<out IViewModelState>> : Fragment() {

    val root: RootActivity
        get() = activity as RootActivity

    open val prepareToolbar: (RootActivity.ToolbarBuilder.() -> Unit)? = null

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        root
            .toolbarBuilder
            .invalidate()
            .prepare(prepareToolbar)
            .build()
    }

    private fun setToolbar(){
        root
            .toolbarBuilder
            .invalidate()
            .prepare(prepareToolbar)
            .build()
    }
}