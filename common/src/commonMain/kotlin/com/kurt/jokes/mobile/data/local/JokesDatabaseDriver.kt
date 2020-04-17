package com.kurt.jokes.mobile.data.local

import com.squareup.sqldelight.db.SqlDriver

expect object JokesDatabaseDriver {
    fun getDriver(): SqlDriver
}