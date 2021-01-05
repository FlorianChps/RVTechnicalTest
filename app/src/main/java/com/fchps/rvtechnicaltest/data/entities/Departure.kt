package com.fchps.rvtechnicaltest.data.entities

import com.squareup.moshi.Json

data class Departure(
    @field:Json(name = "display_informations") val displayInformations: DisplayInformations,
    @field:Json(name = "stop_date_time") val stopDateTime: StopDateTime
)

data class DisplayInformations(
    @field:Json(name = "code") val code: String,
    @field:Json(name = "color") val color: String,
    @field:Json(name = "commercial_mode") val commercialMode: String,
    @field:Json(name = "direction") val direction: String,
    @field:Json(name = "label") val label: String
)

data class StopDateTime(
    @field:Json(name = "departure_date_time") val departureDateTime: String
)