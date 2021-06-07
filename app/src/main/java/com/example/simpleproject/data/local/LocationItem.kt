package com.example.simpleproject.data.local

import java.util.*

data class LocationItem(
    override val id: Int,
    val name: String,
    val type : String,
    val dimension : String,
    val residents : List<String>,
    val url : String,
    val created: String
) : BaseItem