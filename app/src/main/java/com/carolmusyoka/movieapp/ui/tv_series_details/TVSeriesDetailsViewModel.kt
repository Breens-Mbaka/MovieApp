package com.carolmusyoka.movieapp.ui.tv_series_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carolmusyoka.movieapp.data.db.repository.SeriesRepository
import com.carolmusyoka.movieapp.data.model.Event
import com.carolmusyoka.movieapp.data.model.GoToCast
import com.carolmusyoka.movieapp.data.model.GoToVideo
import com.carolmusyoka.movieapp.data.model.entity.Cast
import com.carolmusyoka.movieapp.data.model.entity.TvShowDetails
import com.carolmusyoka.movieapp.data.model.entity.Video
import com.carolmusyoka.movieapp.ui.BaseViewModel
import com.carolmusyoka.movieapp.util.extension.liveDataBlockScope

class TVShowDetailsViewModelFactory(private val tvShowId: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TVShowDetailsViewModel(tvShowId) as T
    }
}

class TVShowDetailsViewModel(tvShowId: Int) : BaseViewModel(), GoToVideo, GoToCast {

    private val tvShowRepository = SeriesRepository()
    val tvShow: LiveData<TvShowDetails>
    val videoList: LiveData<List<Video>>
    val castList: LiveData<List<Cast>>

    override val goToVideoEvent: MutableLiveData<Event<Video>> = MutableLiveData()
    override val goToCastDetailsEvent: MutableLiveData<Event<Cast>> = MutableLiveData()

    init {
        tvShow = liveDataBlockScope {
            tvShowRepository.loadDetails(tvShowId) { mSnackBarText.postValue(Event(it)) }
        }

        videoList = liveDataBlockScope {
            tvShowRepository.loadVideos(tvShowId) { mSnackBarText.postValue(Event(it)) }
        }

        castList = liveDataBlockScope {
            tvShowRepository.loadCredits(tvShowId) { mSnackBarText.postValue(Event(it)) }
        }
    }
}