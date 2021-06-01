package com.carolmusyoka.movieapp.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getPopularMovies() = apiService.getPopularMovies(
        apiKey = "c94f1a52d84cbc62caf7e3d98ea21260",
        page = 1
    )
}