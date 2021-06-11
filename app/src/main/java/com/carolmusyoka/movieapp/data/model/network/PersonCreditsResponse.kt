package com.carolmusyoka.movieapp.data.model.network

import com.carolmusyoka.movieapp.data.model.entity.Credit
import com.carolmusyoka.movieapp.data.model.network.BaseListResponse
import com.google.gson.annotations.SerializedName

data class PersonCreditsResponse(
    @SerializedName("cast")
    override var results: List<Credit>
) : BaseListResponse<Credit>