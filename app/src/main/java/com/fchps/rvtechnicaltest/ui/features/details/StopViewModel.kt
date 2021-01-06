package com.fchps.rvtechnicaltest.ui.features.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fchps.rvtechnicaltest.data.repository.StopRepository
import com.fchps.rvtechnicaltest.utils.ioToMainThreadObservableTransformer
import com.fchps.rvtechnicaltest.utils.store
import io.reactivex.disposables.CompositeDisposable

class StopViewModel @ViewModelInject constructor(
    private val stopRepository: StopRepository
) : ViewModel() {

    val stopLiveData: LiveData<StopState> get() = _stopLiveData

    private val _stopLiveData = MutableLiveData<StopState>()
    private val transformer = ioToMainThreadObservableTransformer<StopState>()
    private val compositeDisposable = CompositeDisposable()

    fun getDetails(stopId: String) {
        stopRepository.getStopDetails(stopId)
            .map<StopState> { list -> StopState.Success(list) }
            .onErrorReturn { error -> StopState.Error(error) }
            .startWith(StopState.Loading)
            .compose(transformer)
            .subscribe { state -> _stopLiveData.value = state  }
            .store(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}