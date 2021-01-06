package com.fchps.rvtechnicaltest.data.repository

import com.fchps.rvtechnicaltest.data.entities.Departure
import com.fchps.rvtechnicaltest.data.remote.StopRemoteDataSource
import com.fchps.rvtechnicaltest.ui.features.details.StopDetailsModel
import com.fchps.rvtechnicaltest.ui.features.details.TransportType
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class StopRepository @Inject constructor(
    private val remoteDataSource: StopRemoteDataSource
) : IStopRepository {

    companion object {
        const val DATE_SERVER_FORMAT = "yyyyMMDD'T'HHmmss"
        const val DATE_UI_FORMAT = "HH:mm"
    }

    override fun getStopDetails(stopId: String): Observable<List<StopDetailsModel>> {
        return remoteDataSource.getStopDetails(stopId).map { result ->
            result.departures.map { mapDepartureToStopDetailsModel(it) }
        }
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
        val parser = SimpleDateFormat(DATE_SERVER_FORMAT, Locale.FRANCE)
        val formatter = SimpleDateFormat(DATE_UI_FORMAT, Locale.FRANCE)
        return formatter.format(parser.parse(departureDateTime))
    }

    private fun mapCommercialModeToType(commercialMode: String): TransportType {
        return when (commercialMode) {
            "Bus" -> TransportType.BUS
            "Métro" -> TransportType.SUB
            "RER" -> TransportType.RER
            "Train" -> TransportType.RER
            "Tramway" -> TransportType.RER
            else -> throw IllegalArgumentException("This $commercialMode value does not match with a TransportType")
        }
    }
}