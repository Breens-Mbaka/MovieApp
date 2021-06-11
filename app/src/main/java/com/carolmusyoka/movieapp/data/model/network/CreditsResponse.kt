package com.carolmusyoka.movieapp.data.model.network

import com.carolmusyoka.movieapp.data.model.entity.Cast
import com.carolmusyoka.movieapp.data.model.network.BaseListResponse
import com.google.gson.annotations.SerializedName

data class CreditsResponse(
    @SerializedName("cast")
    override var results: List<Cast>
) : BaseListResponse<Cast>


