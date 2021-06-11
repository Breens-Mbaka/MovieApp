package com.carolmusyoka.movieapp.data.model.network

import com.carolmusyoka.movieapp.data.model.entity.Movie
import com.carolmusyoka.movieapp.data.model.network.BasePageListResponse
import com.google.gson.annotations.SerializedName


data class MoviesResponse(
    @SerializedName("page")
    override var page: Int,

    @SerializedName("results")
    override var results: List<Movie>
) : BasePageListResponse<Movie>
