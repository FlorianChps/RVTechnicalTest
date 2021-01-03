package com.fchps.rvtechnicaltest.data.entities

import com.squareup.moshi.Json

data class PlaceResult(
    @field:Json(name = "places") val places: List<Place>,
)

data class CommercialMode(
    val id: String,
    val name: String
)

data class CoordX(
    val lat: String,
    val lon: String
)

data class Line(
    val code: String,
    val color: String,
    val commercial_mode: CommercialModeX,
    val id: String,
    val name: String,
    val network: Network,
    val physical_modes: List<PhysicalMode>,
    val text_color: String
)

data class PhysicalModeX(
    val id: String,
    val name: String
)

data class Coord(
    val lat: String,
    val lon: String
)

data class CommercialModeX(
    val id: String,
    val name: String
)

data class Network(
    val id: String,
    val name: String
)

data class PhysicalMode(
    val id: String,
    val name: String
)