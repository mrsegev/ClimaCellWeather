package com.yossisegev.climacellweather.weather.data

import okhttp3.Interceptor
import okhttp3.Response

class RequestHeaderInterceptor(val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("apiKey", apiKey)
                .addHeader("Content-Type", "application/JSON")
                .build()
        )
    }
}