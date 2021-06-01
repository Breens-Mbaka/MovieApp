package com.carolmusyoka.movieapp.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://api.themoviedb.org/"

    val logginIn: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val okhttp: OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(logginIn)
        .build()

    private fun getRetrofit(): Retrofit {
        val builder = Retrofit.Builder()
        return builder
            .baseUrl(BASE_URL)
            .client(okhttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService = getRetrofit().create(ApiService::class.java)
}
