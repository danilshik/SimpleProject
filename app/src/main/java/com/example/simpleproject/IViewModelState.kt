package com.example.simpleproject

import androidx.lifecycle.SavedStateHandle

interface IViewModelState {
    fun save(outState: SavedStateHandle) {
        // default implementation
    }

    /**
     * Переопределить, если нужно сохранить в Bundle
     */
    fun restore(savedState: SavedStateHandle) : IViewModelState {
        // default implementation
        return this
    }
}