package com.example.simpleproject.ui.location_detail

import com.example.simpleproject.ui.base.BaseViewModel

//Незачем запрашивать Location, если все необходимые параметры можно передать через Bundle
class LocationDetailViewModel : BaseViewModel<LocationDetailState>(LocationDetailState.Loaded){
}