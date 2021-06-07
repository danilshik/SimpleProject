package com.example.simpleproject.ui.location_detail

import com.example.simpleproject.IViewModelState

sealed class LocationDetailState : IViewModelState{
    object Loaded : LocationDetailState()
}