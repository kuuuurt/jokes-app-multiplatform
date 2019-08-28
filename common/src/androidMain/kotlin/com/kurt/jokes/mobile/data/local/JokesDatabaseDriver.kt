package com.kurt.jokes.mobile.data.local

import android.content.Context
import com.kurt.jokes.JokesDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual object JokesDatabaseDriver {
    actual var jokesDatabaseDriver: SqlDriver? = null

    fun createDriver(context: Context) {
        jokesDatabaseDriver = AndroidSqliteDriver(JokesDatabase.Schema, context, "asdf.db")
    }
}

