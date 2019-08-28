package com.kurt.jokes.mobile.data

import io.ktor.client.engine.ios.Ios

actual val engine by lazy { Ios.create() }