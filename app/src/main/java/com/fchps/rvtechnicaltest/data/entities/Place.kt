package com.fchps.rvtechnicaltest.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Place(
    val embedded_type: String,
    val id: String,
    val name: String
) : Parcelable