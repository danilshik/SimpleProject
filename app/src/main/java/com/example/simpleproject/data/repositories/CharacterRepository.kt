package com.example.simpleproject.data.repositories

import com.example.simpleproject.data.BaseListResource
import com.example.simpleproject.data.RestService
import com.example.simpleproject.data.local.CharacterListItem
import com.example.simpleproject.data.remote.response.CharacterListResource
import com.example.simpleproject.extensions.mappers.toMapItem
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterRepository(private val apiService : RestService) : CharacterRepositoryContract {
    var defaultPage = 0
    var page = defaultPage

    /**
     * Не знаю,
     */
    override fun getCharacters(isNewPage : Boolean): Single<BaseListResource<CharacterListItem>> {
        if(!isNewPage)
            page = defaultPage
        return apiService.getCharacterList(page)
            .map {
                BaseListResource(
                    it.results.map { item -> item.toMapItem() },
                    it.info.next.isNullOrEmpty()
                )
            }


            .doOnSubscribe{
                page+= 1
            }

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

interface CharacterRepositoryContract{
    fun getCharacters(isNewPage: Boolean) : Single<BaseListResource<CharacterListItem>>
}