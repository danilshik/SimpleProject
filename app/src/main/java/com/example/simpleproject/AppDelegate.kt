package com.example.simpleproject

import android.app.Application
import com.example.simpleproject.di.components.AppComponent
import com.example.simpleproject.di.components.DaggerAppComponent
import com.example.simpleproject.di.modules.AppModule
import com.example.simpleproject.di.modules.NetworkModule
import com.example.simpleproject.di.modules.RepositoryModule

class AppDelegate : Application() {
    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .repositoryModule(RepositoryModule())
            .build()
    }
}