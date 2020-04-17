package com.kurt.jokes.mobile.data.remote

import io.ktor.client.engine.android.Android


actual val engine by lazy { Android.create() }