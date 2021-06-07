package com.example.simpleproject.ui.episode_list

import com.example.simpleproject.IViewModelState
import com.example.simpleproject.data.local.CharacterListItem
import com.example.simpleproject.data.local.EpisodeItem


sealed class EpisodeListState : IViewModelState {
    object Loading : EpisodeListState()
    object ClearData: EpisodeListState()
    data class Error(val message : String, val error : Throwable) : EpisodeListState()
    data class Result(val episodes : List<EpisodeItem>, val isLastPage : Boolean = false) : EpisodeListState()
}