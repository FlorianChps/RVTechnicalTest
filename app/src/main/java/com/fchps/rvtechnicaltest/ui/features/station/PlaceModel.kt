package com.fchps.rvtechnicaltest.ui.features.station

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlaceModel(
    val id: String,
    val name: String,
    var isFavorite: Boolean
) : Parcelable