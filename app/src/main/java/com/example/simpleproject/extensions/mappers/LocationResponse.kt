package com.example.simpleproject.extensions.mappers

import com.example.simpleproject.data.local.LocationItem
import com.example.simpleproject.data.remote.response.LocationResponse
import java.util.*

fun LocationResponse.toMapItem(): LocationItem {
    return LocationItem(
            id = this.id,
            name = this.name,
            type = this.type,
            dimension = this.dimension,
            residents = this.residents,
            url = this.url,
            created = this.created
    )

}