package com.example.simpleproject.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeListResponse(
    val id: Int,
    val name: String,
    @Json(name="air_date")
    val airDate: String,
    val episode : String,
    val characters : List<String>,
    val url: String,
    val created: String
)