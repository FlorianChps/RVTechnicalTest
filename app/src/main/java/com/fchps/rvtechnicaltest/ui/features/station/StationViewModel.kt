package com.fchps.rvtechnicaltest.ui.features.station

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fchps.rvtechnicaltest.data.repository.PlaceRepository
import com.fchps.rvtechnicaltest.utils.ioToMainThreadCompletableTransformer
import com.fchps.rvtechnicaltest.utils.ioToMainThreadObservableTransformer
import com.fchps.rvtechnicaltest.utils.store
import io.reactivex.rxjava3.disposables.CompositeDisposable

class StationViewModel @ViewModelInject constructor(
    private val stationRepository: PlaceRepository
) : ViewModel() {

    val stationLiveData: LiveData<List<PlaceModel>> get() = _stationLiveData

    private val _stationLiveData = MutableLiveData<List<PlaceModel>>()
    private val transformer = ioToMainThreadObservableTransformer<List<PlaceModel>>()
    private val compositeDisposable = CompositeDisposable()

    fun getPlaces(text: String) {
        stationRepository.getPlaces(text)
            .compose(transformer)
            .subscribe(
                { places -> _stationLiveData.value = places },
                { error -> Log.e("ERROR", error.localizedMessage) })
            .store(compositeDisposable)
    }

    fun getLocalPlaces() {
        stationRepository.getLocalPlaces()
            .compose(transformer)
            .subscribe(
                { places -> _stationLiveData.value = places },
                { error -> Log.e("ERROR", error.localizedMessage) })
            .store(compositeDisposable)
    }

    fun savePlace(placeModel: PlaceModel) {
        stationRepository.savePlace(placeModel)
            .compose(ioToMainThreadCompletableTransformer())
            .subscribe(
                {},
                { Log.i("StationViewModel", "SAVE ERROR") },
            )
            .store(compositeDisposable)
    }

    fun deletePlace(placeModel: PlaceModel) {
        stationRepository.deletePlace(placeModel)
            .compose(ioToMainThreadCompletableTransformer())
            .subscribe({},
                { Log.i("StationViewModel", "DELETE ERROR") })
            .store(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}