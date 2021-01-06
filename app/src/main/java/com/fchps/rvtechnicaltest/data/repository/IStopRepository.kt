package com.fchps.rvtechnicaltest.data.repository

import com.fchps.rvtechnicaltest.ui.features.details.StopDetailsModel
import io.reactivex.Observable

interface IStopRepository {

    fun getStopDetails(stopId: String): Observable<List<StopDetailsModel>>
}