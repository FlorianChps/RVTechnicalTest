package com.fchps.rvtechnicaltest.data.remote

import com.fchps.rvtechnicaltest.data.NavitiaService
import com.fchps.rvtechnicaltest.data.entities.PlaceResult
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PlaceRemoteDataSource @Inject constructor(
    private val navitiaService: NavitiaService
) {

    fun getPlaces(searched: String): Single<PlaceResult> = navitiaService.getPlaces(searched)
}