package com.example.simpleproject.data.local

import com.squareup.moshi.Json

data class EpisodeItem(
    override val id: Int,
    val name: String,
    val airDate: String,
    val episode : String,
    val characters : List<String>,
    val url: String,
    val created: String
) : BaseItem