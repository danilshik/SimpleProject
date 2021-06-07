package com.example.simpleproject.ui.episode_detail

import com.example.simpleproject.ui.base.BaseViewModel
import com.example.simpleproject.ui.character_detail.CharacterDetailState


//Незачем запрашивать Episode, если все необходимые параметры можно передать через Bundle
class EpisodeDetailViewModel : BaseViewModel<EpisodeDetailState>(EpisodeDetailState.Loaded){
}