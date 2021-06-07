package com.example.simpleproject.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    val info : BaseInfoResponse,
    val results : T
)