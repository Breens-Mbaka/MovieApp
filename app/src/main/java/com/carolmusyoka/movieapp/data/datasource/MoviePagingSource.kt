package com.carolmusyoka.movieapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.carolmusyoka.movieapp.data.api.ApiService
import com.carolmusyoka.movieapp.data.model.Result

class MoviePagingSource (
    val movieApiService: ApiService,
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPage = params.key ?: 1
            val response = movieApiService.getPopularMovies(
                apiKey = "c94f1a52d84cbc62caf7e3d98ea21260",
                page = nextPage)

            return LoadResult.Page(
                data = response.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = response.page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        TODO("Not yet implemented")
    }
}