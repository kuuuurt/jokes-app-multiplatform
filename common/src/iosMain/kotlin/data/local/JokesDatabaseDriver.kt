package com.kurt.jokes.mobile.data.local

import com.kurt.jokes.JokesDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver

actual object JokesDatabaseDriver {
    actual var jokesDatabaseDriver: SqlDriver? = NativeSqliteDriver(JokesDatabase.Schema, "jokes.db")
}