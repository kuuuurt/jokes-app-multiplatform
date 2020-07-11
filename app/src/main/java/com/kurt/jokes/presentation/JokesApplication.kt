package com.kurt.jokes.presentation

import android.app.Application
import com.kurt.jokes.mobile.data.local.JokesDatabaseDriver.context

class JokesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }
}