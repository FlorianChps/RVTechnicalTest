package com.fchps.rvtechnicaltest.data.repository

import com.fchps.rvtechnicaltest.data.entities.StopResult
import com.fchps.rvtechnicaltest.data.remote.StopRemoteDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class StopRepository @Inject constructor(
    private val remoteDataSource: StopRemoteDataSource
) {

    fun getStopDetails(stopId: String): Single<StopResult> = remoteDataSource.getStopDetails(stopId)
}