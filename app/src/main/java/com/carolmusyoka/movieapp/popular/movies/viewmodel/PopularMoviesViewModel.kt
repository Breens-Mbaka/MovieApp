package com.carolmusyoka.movieapp.popular.movies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.carolmusyoka.movieapp.data.api.RetrofitBuilder.apiService
import com.carolmusyoka.movieapp.data.datasource.MoviePagingSource
import com.carolmusyoka.movieapp.data.model.Result
import com.carolmusyoka.movieapp.data.repository.PopularRepository
import com.carolmusyoka.movieapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class PopularMoviesViewModel (private val teamRepository: PopularRepository)
    : ViewModel(){
    fun getPopularMovies() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = teamRepository.getPopularMovies()))
            Log.d("TAG", "getTeams: Success")
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    val movies: Flow<PagingData<Result>> = Pager(PagingConfig(pageSize = 20)){
        MoviePagingSource(apiService)
    }.flow
        .cachedIn(viewModelScope)
}