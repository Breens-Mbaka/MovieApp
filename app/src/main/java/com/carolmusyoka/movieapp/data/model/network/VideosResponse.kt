package com.carolmusyoka.movieapp.data.model.network

import com.carolmusyoka.movieapp.data.model.entity.Video
import com.carolmusyoka.movieapp.data.model.network.BaseListResponse
import com.google.gson.annotations.SerializedName

data class VideosResponse(
    @SerializedName("results")
    override var results: List<Video>
) : BaseListResponse<Video>
