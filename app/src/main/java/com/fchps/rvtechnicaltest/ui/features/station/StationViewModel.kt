package com.fchps.rvtechnicaltest.ui.features.station

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fchps.rvtechnicaltest.data.repository.PlaceRepository
import com.fchps.rvtechnicaltest.utils.ioToMainThreadCompletableTransformer
import com.fchps.rvtechnicaltest.utils.ioToMainThreadObservableTransformer
import com.fchps.rvtechnicaltest.utils.store
import io.reactivex.disposables.CompositeDisposable

class StationViewModel @ViewModelInject constructor(
    private val stationRepository: PlaceRepository
) : ViewModel() {

    val stationLiveData: LiveData<StationState> get() = _stationLiveData

    private val _stationLiveData = MutableLiveData<StationState>()
    private val transformer = ioToMainThreadObservableTransformer<StationState>()
    private val compositeDisposable = CompositeDisposable()

    fun getPlaces(text: String) {
        stationRepository.getPlaces(text)
            .map<StationState> { list -> StationState.Success(list) }
            .onErrorReturn { error -> StationState.Error(error) }
            .startWith(StationState.Loading)
            .compose(transformer)
            .subscribe { state -> _stationLiveData.value = state }
            .store(compositeDisposable)
    }

    fun getLocalPlaces() {
        stationRepository.getLocalPlaces()
            .map<StationState> { list -> StationState.Success(list) }
            .onErrorReturn { error -> StationState.Error(error) }
            .startWith(StationState.Loading)
            .compose(transformer)
            .subscribe { state -> _stationLiveData.value = state }
            .store(compositeDisposable)
    }

    fun savePlace(placeModel: PlaceModel) {
        stationRepository.savePlace(placeModel)
            .compose(ioToMainThreadCompletableTransformer())
            .subscribe(
                {},
                {
                    _stationLiveData.value =
                        StationState.Error(Throwable("Error while saving Favorite from DB."))
                },
            )
            .store(compositeDisposable)
    }

    fun deletePlace(placeModel: PlaceModel) {
        stationRepository.deletePlace(placeModel)
            .compose(ioToMainThreadCompletableTransformer())
            .subscribe({},
                {
                    _stationLiveData.value =
                        StationState.Error(Throwable("Error while deleting Favorite from DB."))
                })
            .store(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}