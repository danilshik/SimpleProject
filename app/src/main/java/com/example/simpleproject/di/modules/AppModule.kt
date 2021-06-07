package com.example.simpleproject.di.modules

import android.content.Context
import com.example.simpleproject.AppDelegate
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app : AppDelegate) {

    @Provides
    @Singleton
    fun provideContext() : Context = app
}