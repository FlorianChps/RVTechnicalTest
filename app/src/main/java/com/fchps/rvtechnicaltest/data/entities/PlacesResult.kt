package com.fchps.rvtechnicaltest.data.entities

import com.squareup.moshi.Json

data class PlaceResult(
    @field:Json(name = "places") val places: List<Place>,
)