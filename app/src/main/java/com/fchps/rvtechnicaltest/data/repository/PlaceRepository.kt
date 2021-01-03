package com.fchps.rvtechnicaltest.data.repository

import com.fchps.rvtechnicaltest.data.entities.PlaceResult
import com.fchps.rvtechnicaltest.data.remote.PlaceRemoteDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PlaceRepository @Inject constructor(
    private val remoteDataSource: PlaceRemoteDataSource
) : IPlaceRepository {

    override fun getPlaces(searched: String): Single<PlaceResult> =
        remoteDataSource.getPlaces(searched)
}