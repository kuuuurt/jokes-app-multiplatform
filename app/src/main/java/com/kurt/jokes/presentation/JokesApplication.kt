package com.kurt.jokes.presentation

import android.app.Application
import com.kurt.jokes.mobile.data.local.JokesDatabaseDriver
import com.kurt.jokes.mobile.data.local.JokesDatabaseDriver.context
import com.kurt.jokes.mobile.di.ServiceLocator

class JokesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }
}