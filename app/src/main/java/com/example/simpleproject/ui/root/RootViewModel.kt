package com.example.simpleproject.ui.root

import androidx.lifecycle.SavedStateHandle
import com.example.simpleproject.ui.base.BaseViewModel
import com.example.simpleproject.IViewModelState

class RootViewModel(handle: SavedStateHandle) : BaseViewModel<RootState>(RootState()){

}

class RootState : IViewModelState


