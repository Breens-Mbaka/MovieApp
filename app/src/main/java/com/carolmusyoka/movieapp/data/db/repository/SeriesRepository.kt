package com.carolmusyoka.movieapp.data.db.repository

import androidx.lifecycle.MutableLiveData
import com.carolmusyoka.movieapp.data.db.remote.ApiService
import com.carolmusyoka.movieapp.data.model.entity.Cast
import com.carolmusyoka.movieapp.data.model.entity.TvSeries
import com.carolmusyoka.movieapp.data.model.entity.TvShowDetails
import com.carolmusyoka.movieapp.data.model.entity.Video
import com.carolmusyoka.movieapp.util.RetrofitBuilder


class SeriesRepository : BaseRepository() {
    private val tvService =
        RetrofitBuilder.buildService(ApiService.TvService::class.java)

    suspend fun loadDiscoverList(id: Int, errorText: (String) -> Unit) =
        loadPageListCall(
            { tvService.fetchDiscoveryList(id) },
            MutableLiveData<List<TvSeries>>(),
            errorText
        )

    suspend fun loadDetails(id: Int, errorText: (String) -> Unit) =
        loadCall({ tvService.fetchDetails(id) }, MutableLiveData<TvShowDetails>(), errorText)

    suspend fun loadCredits(id: Int, errorText: (String) -> Unit) =
        loadListCall({ tvService.fetchCredits(id) }, MutableLiveData<List<Cast>>(), errorText)

    suspend fun loadVideos(id: Int, errorText: (String) -> Unit) =
        loadListCall({ tvService.fetchVideos(id) }, MutableLiveData<List<Video>>(), errorText)
}