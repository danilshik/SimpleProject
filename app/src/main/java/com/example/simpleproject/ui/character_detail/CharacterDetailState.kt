package com.example.simpleproject.ui.character_detail

import com.example.simpleproject.IViewModelState

sealed class CharacterDetailState  : IViewModelState {
    object Loaded : CharacterDetailState()
}