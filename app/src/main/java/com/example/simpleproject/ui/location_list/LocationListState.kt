package com.example.simpleproject.ui.location_list

import com.example.simpleproject.IViewModelState
import com.example.simpleproject.data.local.LocationItem
import com.example.simpleproject.ui.BaseListState

sealed class LocationListState : IViewModelState {
    object Loading : LocationListState()
    data class Error(val message : String, val error : Throwable) : LocationListState()
    data class Result(val locations : List<LocationItem>, val isLastPage : Boolean = false) : LocationListState()
}