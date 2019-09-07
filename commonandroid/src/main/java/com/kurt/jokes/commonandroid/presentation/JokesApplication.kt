package com.kurt.jokes.commonandroid.presentation

import android.app.Application
import com.kurt.jokes.mobile.data.local.JokesDatabaseDriver
import com.kurt.jokes.mobile.di.ServiceLocator

class JokesApplication : Application() {

    val serviceLocator: ServiceLocator by lazy {
        ServiceLocator()
    }

    override fun onCreate() {
        super.onCreate()
        JokesDatabaseDriver.createDriver(this)
    }
}