package com.fchps.rvtechnicaltest.data.entities

import com.squareup.moshi.Json

data class StopResult(
    @field:Json(name = "departures") val departures: List<Departure>
)