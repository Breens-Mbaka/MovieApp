package com.carolmusyoka.movieapp.ui.person_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carolmusyoka.movieapp.data.db.repository.PersonRepository
import com.carolmusyoka.movieapp.data.model.Event
import com.carolmusyoka.movieapp.data.model.GoToCredit
import com.carolmusyoka.movieapp.data.model.GoToImage
import com.carolmusyoka.movieapp.data.model.entity.Credit
import com.carolmusyoka.movieapp.data.model.entity.Image
import com.carolmusyoka.movieapp.data.model.entity.Person
import com.carolmusyoka.movieapp.ui.BaseViewModel
import com.carolmusyoka.movieapp.util.extension.liveDataBlockScope

class PersonDetailsViewModelFactory(private val personId: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PersonDetailsViewModel(personId) as T
    }
}

class PersonDetailsViewModel(personId: Int) : BaseViewModel(), GoToImage, GoToCredit {

    private val personRepository = PersonRepository()
    val person: LiveData<Person> = liveDataBlockScope {
        personRepository.loadDetails(personId) { mSnackBarText.postValue(Event(it)) }
    }
    val imageList: LiveData<List<Image>> = liveDataBlockScope {
        personRepository.loadImages(personId) { mSnackBarText.postValue(Event(it)) }
    }
    val creditList: LiveData<List<Credit>> = liveDataBlockScope {
        personRepository.loadCredits(personId) { mSnackBarText.postValue(Event(it)) }
    }

    override val goToImageEvent: MutableLiveData<Event<Image>> = MutableLiveData()
    override val goToCreditEvent: MutableLiveData<Event<Credit>> = MutableLiveData()

}