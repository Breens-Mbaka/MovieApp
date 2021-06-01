package com.carolmusyoka.movieapp.popular.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carolmusyoka.movieapp.data.api.ApiHelper
import com.carolmusyoka.movieapp.data.repository.PopularRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val apiHelper: ApiHelper)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularMoviesViewModel::class.java)) {
            return PopularMoviesViewModel(PopularRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}