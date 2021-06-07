package com.example.simpleproject.di.components

import androidx.fragment.app.Fragment
import com.example.simpleproject.di.modules.AppModule
import com.example.simpleproject.di.modules.NetworkModule
import com.example.simpleproject.di.modules.RepositoryModule
import com.example.simpleproject.ui.character_detail.CharacterDetailFragment
import com.example.simpleproject.ui.character_list.CharacterListFragment
import com.example.simpleproject.ui.episode_detail.EpisodeDetailFragment
import com.example.simpleproject.ui.episode_list.EpisodeListFragment
import com.example.simpleproject.ui.location_detail.LocationDetailFragment
import com.example.simpleproject.ui.location_list.LocationListFragment
import com.example.simpleproject.ui.root.RootActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(fragment : LocationListFragment)
    fun inject(fragment : CharacterListFragment)
    fun inject(fragment : EpisodeListFragment)
    fun inject(fragment : LocationDetailFragment)
    fun inject(fragment : CharacterDetailFragment)
    fun inject(fragment : EpisodeDetailFragment)
    fun inject(activity : RootActivity)
}