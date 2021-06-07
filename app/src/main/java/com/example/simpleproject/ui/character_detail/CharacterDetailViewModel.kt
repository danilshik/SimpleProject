package com.example.simpleproject.ui.character_detail

import com.example.simpleproject.ui.base.BaseViewModel
import com.example.simpleproject.ui.location_detail.LocationDetailState



//Незачем запрашивать Character, если все необходимые параметры можно передать через Bundle
class CharacterDetailViewModel : BaseViewModel<CharacterDetailState>(CharacterDetailState.Loaded){
}