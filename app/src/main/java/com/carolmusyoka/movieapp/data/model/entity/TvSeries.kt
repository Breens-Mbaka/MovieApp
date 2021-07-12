package com.carolmusyoka.movieapp.data.model.entity

import com.google.gson.annotations.SerializedName

data class TvSeries(
    @SerializedName("id")
    var id: Int,

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("backdrop_path")
    var backdropPath: String,

    @SerializedName("name")
    var title: String,

    @SerializedName("vote_average")
    var voteAverage: Float,

    @SerializedName("genre_ids")
    var genreIds: List<Int>,
)