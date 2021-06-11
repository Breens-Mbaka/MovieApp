package com.carolmusyoka.movieapp.data.model.entity

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id")
    var id: String,

    @SerializedName("key")
    var key: String,

    @SerializedName("name")
    var name: String,
)