package com.fchps.rvtechnicaltest.di

import android.content.Context
import com.fchps.rvtechnicaltest.data.db.PlaceDataBaseDao
import com.fchps.rvtechnicaltest.data.db.PlaceDatabase
import com.fchps.rvtechnicaltest.data.local.PlaceLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataLocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = PlaceDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun providePlaceDao(db: PlaceDatabase) = db.placesDatabaseDao()

    @Singleton
    @Provides
    fun providePlaceLocalDataSource(dao: PlaceDataBaseDao) = PlaceLocalDataSource(dao)

}