package com.fchps.rvtechnicaltest.ui.features.details

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fchps.rvtechnicaltest.data.entities.Departure
import com.fchps.rvtechnicaltest.data.repository.StopRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class StopViewModel @ViewModelInject constructor(
    private val stopRepository: StopRepository
) : ViewModel() {

    val stopLiveData: LiveData<List<StopDetailsModel>> get() = _stopLiveData

    private val _stopLiveData = MutableLiveData<List<StopDetailsModel>>()
    private val disposable = CompositeDisposable()

    fun getDetails(stopId: String) {
        disposable.add(stopRepository.getStopDetails(stopId)
            .subscribeOn(Schedulers.io())
            .map { result ->
                result.departures.map { mapDepartureToStopDetailsModel(it) }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list -> _stopLiveData.value = list },
                { error -> Log.e("ERROR", error.localizedMessage) }
            ))
    }

    private fun mapDepartureToStopDetailsModel(departure: Departure): StopDetailsModel =
        StopDetailsModel(
            mapCommercialModeToType(departure.displayInformations.commercialMode),
            "#${departure.displayInformations.color}",
            departure.displayInformations.code,
            formatDepartureHour(departure.stopDateTime.departureDateTime),
            departure.displayInformations.direction
        )

    private fun formatDepartureHour(departureDateTime: String): String {
        val parser = SimpleDateFormat("yyyyMMDD'T'HHmmss", Locale.FRANCE)
        val formatter = SimpleDateFormat("HH:mm", Locale.FRANCE)
        return formatter.format(parser.parse(departureDateTime))
    }

    private fun mapCommercialModeToType(commercialMode: String): TransportType {
        return when (commercialMode) {
            "Bus" -> TransportType.BUS
            "Métro" -> TransportType.SUB
            "RER" -> TransportType.RER
            "Train" -> TransportType.RER
            else -> throw IllegalArgumentException("This $commercialMode value does not match with a TransportType")
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}