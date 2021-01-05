package com.fchps.rvtechnicaltest.data.remote

import com.fchps.rvtechnicaltest.data.NavitiaService
import javax.inject.Inject

class StopRemoteDataSource @Inject constructor(
    private val navitiaService: NavitiaService
) {

    fun getStopDetails(idStop: String) = navitiaService.getStopDetails(idStop)
}