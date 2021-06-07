package com.example.simpleproject.data.repositories

import com.example.simpleproject.data.BaseListResource
import com.example.simpleproject.data.RestService
import com.example.simpleproject.data.local.LocationItem
import com.example.simpleproject.extensions.mappers.toMapItem
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocationRepository(private val apiService : RestService) : LocationRepositoryContract {
    var defaultPage = -1
    var page = defaultPage


    override fun getLocations(isNewPage : Boolean): Single<BaseListResource<LocationItem>> {
        if(!isNewPage)
            page = defaultPage
        page+= 1

        return apiService.getLocationList(page)
            .map {
                BaseListResource(
                    it.results.map { item -> item.toMapItem() },
                    it.info.next.isNullOrEmpty()
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

interface LocationRepositoryContract{
    fun getLocations(isNewPage: Boolean) : Single<BaseListResource<LocationItem>>
}