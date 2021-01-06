package com.fchps.rvtechnicaltest.ui.features.details

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fchps.rvtechnicaltest.data.repository.StopRepository
import com.fchps.rvtechnicaltest.utils.ioToMainThreadObservableTransformer
import com.fchps.rvtechnicaltest.utils.store
import io.reactivex.rxjava3.disposables.CompositeDisposable

class StopViewModel @ViewModelInject constructor(
    private val stopRepository: StopRepository
) : ViewModel() {

    val stopLiveData: LiveData<List<StopDetailsModel>> get() = _stopLiveData

    private val _stopLiveData = MutableLiveData<List<StopDetailsModel>>()
    private val transformer = ioToMainThreadObservableTransformer<List<StopDetailsModel>>()
    private val compositeDisposable = CompositeDisposable()

    fun getDetails(stopId: String) {
        stopRepository.getStopDetails(stopId)
            .compose(transformer)
            .subscribe(
                { list -> _stopLiveData.value = list },
                { error -> Log.e("ERROR", error.localizedMessage) })
            .store(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}