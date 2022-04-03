package com.example.newstestwork.service

import okhttp3.Interceptor
import okhttp3.Interceptor.Companion.invoke
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object JsonService {

    private const val json_URL = "http://188.40.167.45:3001"

    private val loggingInterceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val baseInterceptor: Interceptor = invoke { chain ->
        val newUrl = chain
            .request()
            .url
            .newBuilder()
            .build()

        val request = chain
            .request()
            .newBuilder()
            .url(newUrl)
            .build()

        return@invoke chain.proceed(request)
    }

    private val client: OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(baseInterceptor)
        .build()

    fun retrofitService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(json_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}