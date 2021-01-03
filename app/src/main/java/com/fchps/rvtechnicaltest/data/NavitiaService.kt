package com.fchps.rvtechnicaltest.data

import com.fchps.rvtechnicaltest.data.entities.PlaceResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NavitiaService {

    @GET("coverage/fr-idf/places?key=b56ac04d-d6af-4777-95a2-0ce997442d4a")
    fun getPlaces(@Query("q") searched: String) : Single<PlaceResult>
}