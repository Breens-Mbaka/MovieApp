package com.carolmusyoka.movieapp.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carolmusyoka.movieapp.data.db.repository.MovieRepository
import com.carolmusyoka.movieapp.data.model.Event
import com.carolmusyoka.movieapp.data.model.GoToCast
import com.carolmusyoka.movieapp.data.model.GoToVideo
import com.carolmusyoka.movieapp.data.model.entity.Cast
import com.carolmusyoka.movieapp.data.model.entity.Movie
import com.carolmusyoka.movieapp.data.model.entity.Video
import com.carolmusyoka.movieapp.ui.BaseViewModel
import com.carolmusyoka.movieapp.util.extension.liveDataBlockScope

class MovieDetailsViewModelFactory(private val movieId: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(movieId) as T
    }
}

class MovieDetailsViewModel(movieId: Int) : BaseViewModel(), GoToCast, GoToVideo {

    private val movieRepository = MovieRepository()
    val movie: LiveData<Movie>
    val videoList: LiveData<List<Video>>
    val castList: LiveData<List<Cast>>

    override val goToCastDetailsEvent: MutableLiveData<Event<Cast>> = MutableLiveData()
    override val goToVideoEvent: MutableLiveData<Event<Video>> = MutableLiveData()

    init {
        movie = liveDataBlockScope {
            movieRepository.loadDetails(movieId) { mSnackBarText.postValue(Event(it)) }
        }

        videoList = liveDataBlockScope {
            movieRepository.loadVideos(movieId) { mSnackBarText.postValue(Event(it)) }
        }

        castList = liveDataBlockScope {
            movieRepository.loadCredits(movieId) { mSnackBarText.postValue(Event(it)) }
        }
    }
}
