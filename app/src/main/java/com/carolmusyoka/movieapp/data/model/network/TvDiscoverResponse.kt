package com.carolmusyoka.movieapp.data.model.network

import com.carolmusyoka.movieapp.data.model.entity.TvShow
import com.carolmusyoka.movieapp.data.model.network.BasePageListResponse
import com.google.gson.annotations.SerializedName

data class TvDiscoverResponse(
    @SerializedName("page")
    override var page: Int,

    @SerializedName("results")
    override var results: List<TvShow>
) : BasePageListResponse<TvShow>