package com.example.simpleproject.ui.character_list

import com.example.simpleproject.IViewModelState
import com.example.simpleproject.data.local.CharacterListItem
import com.example.simpleproject.data.local.LocationItem


sealed class CharacterListState : IViewModelState {
    object Loading : CharacterListState()
    data class Error(val message : String, val error : Throwable) : CharacterListState()
    data class Result(val characters : List<CharacterListItem>, val isLastPage : Boolean = false) : CharacterListState()
}