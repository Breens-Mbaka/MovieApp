package com.carolmusyoka.movieapp.ui.binding

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.carolmusyoka.movieapp.R
import com.carolmusyoka.movieapp.data.db.remote.ApiService
import com.carolmusyoka.movieapp.data.model.entity.Movie
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.lang.Exception

@BindingAdapter("bind_backdrop_path_loading_highlighted_movie", "bind_progress")
fun ImageView.bindBackdropImageWithPicassoLoadingMovie(movie: Movie?, progressBar: ProgressBar) {
    if (movie == null) return
    val backdropPath = movie.backdropPath
    if (backdropPath.isNullOrBlank()) {
        this.setImageResource(R.drawable.ic_baseline_image_24)
        return
    }

    progressBar.visibility = View.VISIBLE
    Picasso.get().load(ApiService.getBackdropUrl(backdropPath)).fit()
        .transform(RoundedCornersTransformation(4, 1))
        .error(R.drawable.ic_baseline_image_24)
        .into(this, object : Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                progressBar.visibility = View.GONE
            }
        })
}

@BindingAdapter("bind_backdrop_path_loading", "bind_progress")
fun ImageView.bindBackdropImageWithPicassoLoading(path: String?, progressBar: ProgressBar) {
    if (path.isNullOrBlank()) {
        this.setImageResource(R.drawable.ic_baseline_image_24)
        return
    }
    progressBar.visibility = View.VISIBLE
    Picasso.get().load(ApiService.getBackdropUrl(path)).fit()
        .transform(RoundedCornersTransformation(4, 1))
        .error(R.drawable.ic_baseline_image_24)
        .into(this, object : Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                progressBar.visibility = View.GONE
            }
        })
}

@BindingAdapter("bind_poster_path")
fun ImageView.bindPosterImageWithPicasso(path: String?) {
    if (path.isNullOrBlank()) {
        this.setImageResource(R.drawable.ic_baseline_image_24)
        return
    }
    Picasso.get().load(ApiService.getPosterUrl(path)).fit()
        .transform(RoundedCornersTransformation(4, 1))
        .error(R.drawable.ic_baseline_image_24).into(this)
}

@BindingAdapter("bind_profile_path")
fun ImageView.bindProfileImageWithPicasso(path: String?) {
    if (path.isNullOrBlank()) {
        this.setImageResource(R.drawable.ic_baseline_image_24)
        return
    }
    Picasso.get().load(ApiService.getProfileUrl(path)).fit()
        .error(R.drawable.ic_baseline_image_24).into(this)
}

@BindingAdapter("bind_video_thumbnail")
fun ImageView.bindVideoThumbnailWithPicasso(youtubeId: String?) {
    if (youtubeId.isNullOrBlank()) {
        this.setImageResource(R.drawable.ic_baseline_image_24)
        return
    }
    Picasso.get().load(ApiService.getYoutubeImageUrl(youtubeId)).fit()
        .error(R.drawable.ic_baseline_image_24).into(this)
}

@BindingAdapter("bind_profile_path_no_fit")
fun ImageView.bindProfileImageWithPicassoNoFit(path: String?) {
    if (path.isNullOrBlank()) {
        this.setImageResource(R.drawable.ic_baseline_image_24)
        return
    }
    Picasso.get().load(ApiService.getProfileUrl(path))
        .error(R.drawable.ic_baseline_image_24).into(this)
}
