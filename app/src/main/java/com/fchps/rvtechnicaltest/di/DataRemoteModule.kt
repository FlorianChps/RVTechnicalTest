package com.fchps.rvtechnicaltest.di

import com.fchps.rvtechnicaltest.data.NavitiaService
import com.fchps.rvtechnicaltest.data.local.PlaceLocalDataSource
import com.fchps.rvtechnicaltest.data.remote.PlaceRemoteDataSource
import com.fchps.rvtechnicaltest.data.remote.StopRemoteDataSource
import com.fchps.rvtechnicaltest.data.repository.PlaceRepository
import com.fchps.rvtechnicaltest.data.repository.StopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataRemoteModule {

    @Singleton
    @Provides
    fun providePlaceRemoteDataSource(navitiaService: NavitiaService): PlaceRemoteDataSource =
        PlaceRemoteDataSource(navitiaService)

    @Singleton
    @Provides
    fun providePlaceRepository(
        remoteDataSource: PlaceRemoteDataSource,
        localDataSource: PlaceLocalDataSource
    ) =
        PlaceRepository(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideStopRepository(stopDataSource: StopRemoteDataSource) = StopRepository(stopDataSource)
}