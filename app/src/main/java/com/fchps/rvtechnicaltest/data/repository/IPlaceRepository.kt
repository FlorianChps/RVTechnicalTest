package com.fchps.rvtechnicaltest.data.repository

import com.fchps.rvtechnicaltest.data.entities.PlaceResult
import io.reactivex.rxjava3.core.Single

interface IPlaceRepository {

    fun getPlaces(searched: String) : Single<PlaceResult>
}