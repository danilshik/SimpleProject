package com.example.simpleproject.extensions.mappers

import com.example.simpleproject.data.local.EpisodeItem
import com.example.simpleproject.data.remote.response.EpisodeListResponse
import com.squareup.moshi.Json

fun EpisodeListResponse.toMapItem(): EpisodeItem {
    return EpisodeItem(
        id = this.id,
        name = this.name,
        airDate = this.airDate,
        episode = this.episode,
        characters = this.characters,
        url = this.url,
        created = this.created
    )
}