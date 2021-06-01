package com.carolmusyoka.movieapp.data.repository

import com.carolmusyoka.movieapp.data.api.ApiHelper

class PopularRepository (private val apiHelper: ApiHelper) {
    suspend fun getPopularMovies() = apiHelper.getPopularMovies()
}