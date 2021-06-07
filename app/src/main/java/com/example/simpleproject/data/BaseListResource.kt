package com.example.simpleproject.data



data class BaseListResource<T>(
    val items : List<T>,
    val isLastPage : Boolean = false
)