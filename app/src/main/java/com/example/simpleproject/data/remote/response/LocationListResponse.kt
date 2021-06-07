package com.example.simpleproject.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationListResponse(
    @Json(name = "info")
    val info : BaseInfoResponse,
    @Json(name = "results")
    val results : List<LocationResponse>
)