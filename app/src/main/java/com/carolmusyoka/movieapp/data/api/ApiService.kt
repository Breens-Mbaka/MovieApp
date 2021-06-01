package com.carolmusyoka.movieapp.data.api

import com.carolmusyoka.movieapp.data.model.Popular
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Popular
}