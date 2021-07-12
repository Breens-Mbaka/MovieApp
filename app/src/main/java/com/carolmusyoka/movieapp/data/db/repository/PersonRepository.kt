package com.carolmusyoka.movieapp.data.db.repository

import androidx.lifecycle.MutableLiveData
import com.carolmusyoka.movieapp.data.db.remote.ApiService
import com.carolmusyoka.movieapp.data.model.entity.Credit
import com.carolmusyoka.movieapp.data.model.entity.Image
import com.carolmusyoka.movieapp.data.model.entity.Person
import com.carolmusyoka.movieapp.util.RetrofitBuilder


class PersonRepository : BaseRepository() {
    private val peopleService =
        RetrofitBuilder.buildService(ApiService.PeopleService::class.java)

    suspend fun loadDetails(id: Int, errorText: (String) -> Unit) =
        loadCall({ peopleService.fetchDetails(id) }, MutableLiveData<Person>(), errorText)

    suspend fun loadCredits(id: Int, errorText: (String) -> Unit) =
        loadListCall(
            { peopleService.fetchCredits(id) },
            MutableLiveData<List<Credit>>(),
            errorText
        )

    suspend fun loadImages(id: Int, errorText: (String) -> Unit) =
        loadListCall({ peopleService.fetchImages(id) }, MutableLiveData<List<Image>>(), errorText)
}