package com.fchps.rvtechnicaltest.ui.features.station

sealed class StationState {

    object Loading : StationState()

    data class Success(val stops: List<PlaceModel>) : StationState()

    data class Error(val error: Throwable) : StationState()
}