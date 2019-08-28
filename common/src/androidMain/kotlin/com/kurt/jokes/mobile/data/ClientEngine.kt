package com.kurt.jokes.mobile.data

import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.logging.HttpLoggingInterceptor

actual val engine by lazy {
    OkHttp.create {
        val networkInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        addNetworkInterceptor(networkInterceptor)

    }
}