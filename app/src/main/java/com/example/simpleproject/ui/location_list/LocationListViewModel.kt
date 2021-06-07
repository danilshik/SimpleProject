package com.example.simpleproject.ui.location_list

import com.example.simpleproject.data.repositories.LocationRepositoryContract
import com.example.simpleproject.ui.BaseListState
import com.example.simpleproject.ui.base.BaseViewModel
import com.example.simpleproject.ui.base.Notify
import javax.inject.Inject

class LocationListViewModel @Inject constructor(
    private val repository: LocationRepositoryContract
) : BaseViewModel<LocationListState>(LocationListState.Loading), ILocationListViewModel{



    init {
        loadPageLocationList(false)
    }


    override fun loadPageLocationList(isNewPage : Boolean){
        updateState { LocationListState.Loading }
        repository.getLocations(isNewPage)
            .subscribe(
                { locationsResource ->
                    updateState {
                        LocationListState.Result(
                            locationsResource.items, locationsResource.isLastPage
                        )
                    }
                },
                { throwable ->
                    updateState {
                        LocationListState.Error("При загрузки данных произошла ошибка", throwable)
                    }
                    notify(Notify.TextMessage("При загрузке данных произошла ошибка"))
            }).track()
    }
}

interface ILocationListViewModel{
    fun loadPageLocationList(isNewPage: Boolean = false)
}