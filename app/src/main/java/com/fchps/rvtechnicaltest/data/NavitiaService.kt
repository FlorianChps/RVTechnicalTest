package com.fchps.rvtechnicaltest.data

import com.fchps.rvtechnicaltest.data.entities.PlaceResult
import com.fchps.rvtechnicaltest.data.entities.StopResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NavitiaService {

    @GET("coverage/fr-idf/places")
    fun getPlaces(@Query("q") searched: String): Single<PlaceResult>

    @GET("coverage/fr-idf/stop_areas/{stop_area}/departures")
    fun getStopDetails(@Path("stop_area") stopId: String): Single<StopResult>
}