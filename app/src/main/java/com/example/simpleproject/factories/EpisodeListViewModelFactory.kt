package com.example.simpleproject.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleproject.data.repositories.CharacterRepository
import com.example.simpleproject.data.repositories.EpisodeRepository
import com.example.simpleproject.ui.character_list.CharacterListViewModel
import com.example.simpleproject.ui.episode_list.EpisodeListViewModel
import javax.inject.Inject

class EpisodeListViewModelFactory  @Inject constructor(
    private val episodeRepository: EpisodeRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EpisodeListViewModel::class.java)){
            return EpisodeListViewModel(episodeRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class. See MainViewModelFactory.kt")
    }
}