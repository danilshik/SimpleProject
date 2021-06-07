package com.example.simpleproject.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleproject.data.repositories.CharacterRepository
import com.example.simpleproject.data.repositories.LocationRepository
import com.example.simpleproject.ui.character_list.CharacterListViewModel
import com.example.simpleproject.ui.location_list.LocationListViewModel
import javax.inject.Inject

class CharacterListViewModelFactory  @Inject constructor(
    private val characterListRepository: CharacterRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CharacterListViewModel::class.java)){
            return CharacterListViewModel(characterListRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class. See MainViewModelFactory.kt")
    }
}