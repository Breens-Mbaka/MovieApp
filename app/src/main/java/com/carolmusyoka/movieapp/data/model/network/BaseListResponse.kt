package com.carolmusyoka.movieapp.data.model.network

interface BaseListResponse<T> {
    var results: List<T>
}
