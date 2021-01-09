package com.ashish.pixel.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HttpRequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .addHeader("Authorization","563492ad6f9170000100000195f20e9f81bd43aabfa885a621932996")
            .build()
        Log.d("RequestInterceptor", request.toString())
        return chain.proceed(request)
    }

}