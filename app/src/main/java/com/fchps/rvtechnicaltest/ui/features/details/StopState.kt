package com.fchps.rvtechnicaltest.ui.features.details

sealed class StopState {

    object Loading : StopState()

    data class Success(val stops: List<StopDetailsModel>) : StopState()

    data class Error(val error: Throwable) : StopState()
}

