package com.example.simpleproject.data.remote.response

import com.example.simpleproject.data.local.LocationItem
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CharacterListResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type : String,
    val gender: String,
    val origin : CharacterListLocationResponse,
    val location : CharacterListLocationResponse,
    val image : String,
    val episode : List<String>,
    val url : String,
    val created: String
)
