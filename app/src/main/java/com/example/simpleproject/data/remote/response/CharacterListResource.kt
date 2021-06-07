package com.example.simpleproject.data.remote.response

import com.example.simpleproject.data.local.CharacterListItem

data class CharacterListResource(
    val items : List<CharacterListItem>,
    val isLastPage : Boolean = false
)