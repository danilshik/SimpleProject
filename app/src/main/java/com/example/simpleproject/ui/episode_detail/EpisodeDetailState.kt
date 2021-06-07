package com.example.simpleproject.ui.episode_detail

import com.example.simpleproject.IViewModelState

sealed class EpisodeDetailState  : IViewModelState {
    object Loaded : EpisodeDetailState()
}