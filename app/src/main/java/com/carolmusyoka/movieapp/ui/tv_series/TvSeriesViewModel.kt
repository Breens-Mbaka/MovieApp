package com.carolmusyoka.movieapp.ui.tv_series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.carolmusyoka.movieapp.data.db.repository.SeriesRepository
import com.carolmusyoka.movieapp.data.model.Event
import com.carolmusyoka.movieapp.data.model.GoToTvSeries
import com.carolmusyoka.movieapp.data.model.entity.TvSeries
import com.carolmusyoka.movieapp.ui.BaseViewModel
import com.carolmusyoka.movieapp.util.extension.appendList
import com.carolmusyoka.movieapp.util.extension.liveDataBlockScope

class TvSeriesViewModel : BaseViewModel(), GoToTvSeries {

    private val tvShowRepository = SeriesRepository()
    private val loadedTvSeriesList: LiveData<List<TvSeries>>
    private val tvShowsPage = MutableLiveData<Int>().apply { value = 1 }

    val tvShowList = MediatorLiveData<MutableList<TvSeries>>()

    override val goToTvSeriesEvent: MutableLiveData<Event<TvSeries>> = MutableLiveData()

    init {
        loadedTvSeriesList = tvShowsPage.switchMap {
            liveDataBlockScope {
                tvShowRepository.loadDiscoverList(it) { mSnackBarText.postValue(Event(it)) }
            }
        }
        tvShowList.addSource(loadedTvSeriesList) { it?.let { list -> tvShowList.appendList(list) } }
    }

    fun loadMoreTvShows() {
        tvShowsPage.value = tvShowsPage.value?.plus(1)
    }
}