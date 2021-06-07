package com.example.simpleproject.data.local

interface BaseItem {
    val id : Int

    override fun equals(other: Any?): Boolean
}