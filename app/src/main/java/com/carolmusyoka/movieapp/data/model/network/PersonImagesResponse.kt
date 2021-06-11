package com.carolmusyoka.movieapp.data.model.network

import com.carolmusyoka.movieapp.data.model.entity.Image
import com.carolmusyoka.movieapp.data.model.network.BaseListResponse
import com.google.gson.annotations.SerializedName

data class PersonImagesResponse(
    @SerializedName("profiles")
    override var results: List<Image>
) : BaseListResponse<Image>