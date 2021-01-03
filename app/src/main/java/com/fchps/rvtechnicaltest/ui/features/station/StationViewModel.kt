package com.fchps.rvtechnicaltest.ui.features.station

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fchps.rvtechnicaltest.data.entities.Place
import com.fchps.rvtechnicaltest.data.repository.PlaceRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class StationViewModel @ViewModelInject constructor(
    private val stationRepository: PlaceRepository
) : ViewModel() {

    val stationLiveData: LiveData<List<Place>> get() = _stationLiveData

    private val _stationLiveData = MutableLiveData<List<Place>>()
    private val disposable = CompositeDisposable()

    fun getPlaces(text: String) {
        stationRepository.getPlaces(text)
            .subscribeOn(Schedulers.io())
            .map { result -> result.places }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { places -> _stationLiveData.value = places },
                { error -> Log.e("ERROR", error.localizedMessage) }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}