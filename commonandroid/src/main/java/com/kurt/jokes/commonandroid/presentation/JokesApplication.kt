package com.kurt.jokes.commonandroid.presentation

import android.app.Application
import com.kurt.jokes.mobile.data.local.JokesDatabaseDriver

class JokesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        JokesDatabaseDriver.createDriver(this)
    }
}