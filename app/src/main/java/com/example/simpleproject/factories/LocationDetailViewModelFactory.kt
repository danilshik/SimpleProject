package com.example.simpleproject.factories

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.simpleproject.ui.location_detail.LocationDetailViewModel

//class LocationDetailViewModelFactory(
//    owner: SavedStateRegistryOwner,
//    defaultArgs: Bundle = bundleOf()
//) : AbstractSavedStateViewModelFactory(owner, defaultArgs){
//    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
//        if (modelClass.isAssignableFrom(LocationDetailViewModel::class.java)) {
//            return LocationDetailViewModel(handle, params as String) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}