package com.fchps.rvtechnicaltest.data.remote

import com.fchps.rvtechnicaltest.data.NavitiaService
import com.fchps.rvtechnicaltest.data.entities.PlaceResult
import io.reactivex.Observable
import javax.inject.Inject

class PlaceRemoteDataSource @Inject constructor(
    private val navitiaService: NavitiaService
) {

    fun getPlaces(searched: String): Observable<PlaceResult> = navitiaService.getPlaces(searched)
}