package com.fchps.rvtechnicaltest.ui.features.details

import com.fchps.rvtechnicaltest.R

data class StopDetailsModel(
    val type: TransportType,
    val color: String = "#",
    val code: String,
    val departureHour: String,
    val destination: String
)

enum class TransportType(val resId: Int) {
    BUS(R.drawable.ic_bus),
    RER(R.drawable.ic_rer),
    SUB(R.drawable.ic_sub)
}