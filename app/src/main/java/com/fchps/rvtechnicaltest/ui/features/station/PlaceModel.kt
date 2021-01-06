package com.fchps.rvtechnicaltest.ui.features.station

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "place_favorite_table")
data class PlaceModel(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean
) : Parcelable