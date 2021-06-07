package com.example.simpleproject.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseInfoResponse(
    @Json(name = "count")
    val count : Int,
    @Json(name = "pages")
    val pages : Int,
    @Json(name = "next")
    val next : String?,
    @Json(name = "prev")
    val prev : String?
)