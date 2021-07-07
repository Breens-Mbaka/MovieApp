package com.carolmusyoka.movieapp.ui.tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.carolmusyoka.movieapp.data.db.repository.SeriesRepository
import com.carolmusyoka.movieapp.data.model.Event
import com.carolmusyoka.movieapp.data.model.GoToTvShow
import com.carolmusyoka.movieapp.data.model.entity.TvShow
import com.carolmusyoka.movieapp.ui.BaseViewModel
import com.carolmusyoka.movieapp.util.extension.appendList
import com.carolmusyoka.movieapp.util.extension.liveDataBlockScope

class TvShowsViewModel : BaseViewModel(), GoToTvShow {

    private val tvShowRepository = SeriesRepository()
    private val loadedTvShowList: LiveData<List<TvShow>>
    private val tvShowsPage = MutableLiveData<Int>().apply { value = 1 }

    val tvShowList = MediatorLiveData<MutableList<TvShow>>()

    override val goToTvShowEvent: MutableLiveData<Event<TvShow>> = MutableLiveData()

    init {
        loadedTvShowList = tvShowsPage.switchMap {
            liveDataBlockScope {
                tvShowRepository.loadDiscoverList(it) { mSnackBarText.postValue(Event(it)) }
            }
        }
        tvShowList.addSource(loadedTvShowList) { it?.let { list -> tvShowList.appendList(list) } }
    }

    fun loadMoreTvShows() {
        tvShowsPage.value = tvShowsPage.value?.plus(1)
    }
}