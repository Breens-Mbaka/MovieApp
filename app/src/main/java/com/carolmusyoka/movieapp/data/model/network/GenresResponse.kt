package com.carolmusyoka.movieapp.data.model.network

import com.carolmusyoka.movieapp.data.model.entity.Genre
import com.carolmusyoka.movieapp.data.model.network.BaseListResponse
import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    override var results: List<Genre>
) : BaseListResponse<Genre>
