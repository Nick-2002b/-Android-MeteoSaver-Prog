package com.unibo.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.Interceptor


class RetrofitClient {
    private val baseUrl = "https://open-weather13.p.rapidapi.com"

    private val headersInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("x-rapidapi-host", "open-weather13.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "b4c0d79bc7msh8982ee7faad59d4p156cffjsn6433fe4953ce")
            .build()
        chain.proceed(request)
    }
    private val moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(headersInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val weatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}