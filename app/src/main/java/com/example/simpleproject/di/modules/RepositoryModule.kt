package com.example.simpleproject.di.modules

import android.content.Context
import com.example.simpleproject.AppDelegate
import com.example.simpleproject.data.RestService
import com.example.simpleproject.data.repositories.CharacterRepository
import com.example.simpleproject.data.repositories.EpisodeRepository
import com.example.simpleproject.data.repositories.LocationRepository
import com.example.simpleproject.data.repositories.LocationRepositoryContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton



@Module
class RepositoryModule() {

    @Provides
    @Singleton
    fun provideLocationRepository(apiService: RestService) : LocationRepository =
        LocationRepository(apiService)

    @Provides
    @Singleton
    fun provideCharacterRepository(apiService: RestService) : CharacterRepository =
        CharacterRepository(apiService)

    @Provides
    @Singleton
    fun provideEpisodeRepository(apiService: RestService) : EpisodeRepository =
        EpisodeRepository(apiService)
}