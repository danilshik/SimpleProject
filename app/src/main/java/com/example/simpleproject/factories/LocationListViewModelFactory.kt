package com.example.simpleproject.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleproject.data.repositories.LocationRepository
import com.example.simpleproject.data.repositories.LocationRepositoryContract
import com.example.simpleproject.ui.location_list.LocationListViewModel
import javax.inject.Inject

class LocationListViewModelFactory @Inject constructor(
    private val locationRepository: LocationRepository
    ): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LocationListViewModel::class.java)){
            return LocationListViewModel(locationRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class. See MainViewModelFactory.kt")
    }
}