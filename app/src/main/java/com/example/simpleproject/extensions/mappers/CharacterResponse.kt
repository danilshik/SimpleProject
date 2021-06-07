package com.example.simpleproject.extensions.mappers

import com.example.simpleproject.data.local.CharacterListItem
import com.example.simpleproject.data.local.LocationItem
import com.example.simpleproject.data.remote.response.CharacterListLocationResponse
import com.example.simpleproject.data.remote.response.CharacterListResponse
import com.example.simpleproject.data.remote.response.LocationResponse

fun CharacterListResponse.toMapItem(): CharacterListItem{
    return CharacterListItem(
        id = this.id,
        name  = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        origin = this.origin,
        location = this.location,
        image = this.image,
        episode = this.episode,
        url = this.url,
        created = this.created
    )

}