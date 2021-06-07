package com.example.simpleproject.ui

import com.example.simpleproject.IViewModelState

sealed class BaseListState : IViewModelState {
    object Loading : BaseListState()
    data class Error(val message : String, val error : Throwable) : BaseListState()
    data class Result<T>(val locations : T, val isLastPage : Boolean = false) : BaseListState()
}