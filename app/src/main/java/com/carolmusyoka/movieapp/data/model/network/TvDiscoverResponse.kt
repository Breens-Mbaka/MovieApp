package com.carolmusyoka.movieapp.data.model.network

import com.carolmusyoka.movieapp.data.model.entity.TvSeries
import com.google.gson.annotations.SerializedName

data class TvDiscoverResponse(
    @SerializedName("page")
    override var page: Int,

    @SerializedName("results")
    override var results: List<TvSeries>
) : BasePageListResponse<TvSeries>