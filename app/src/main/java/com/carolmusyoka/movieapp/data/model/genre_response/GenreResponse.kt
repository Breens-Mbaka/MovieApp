package com.carolmusyoka.movieapp.data.model.genre_response

data class GenreResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)