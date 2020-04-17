package com.kurt.jokes.mobile.data.local

import com.kurt.jokes.JokesDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual object JokesDatabaseDriver {
    actual fun getDriver(): SqlDriver = NativeSqliteDriver(JokesDatabase.Schema, "jokes.db")
}