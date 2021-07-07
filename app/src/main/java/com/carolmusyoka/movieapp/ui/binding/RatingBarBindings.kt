package com.carolmusyoka.movieapp.ui.binding

import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.carolmusyoka.movieapp.data.db.remote.ApiService
import com.carolmusyoka.movieapp.data.model.entity.Movie


@BindingAdapter("bind_rating_bar", "bind_rating_stars")
fun RatingBar.bindRatingBar(movie: Movie?, stars: Int) {
    movie?.let { this.rating = stars * ((it.voteAverage / ApiService.MAX_RATING)) }
}

@BindingAdapter("bind_rating_bar_tv_show", "bind_rating_stars")
fun RatingBar.bindRatingBar(voteAverage: Float, stars: Int) {
    this.rating = stars * ((voteAverage / ApiService.MAX_RATING))
}
