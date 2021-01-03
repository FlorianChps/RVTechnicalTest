package com.fchps.rvtechnicaltest.di

import com.fchps.rvtechnicaltest.data.NavitiaService
import com.fchps.rvtechnicaltest.data.remote.PlaceRemoteDataSource
import com.fchps.rvtechnicaltest.data.repository.PlaceRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RVTechnicalModule {

    private const val BASE_URL = "https://api.navitia.io/v1/"

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request()
                val url = request.url.newBuilder()
                    .addQueryParameter("key", "b56ac04d-d6af-4777-95a2-0ce997442d4a")
                    .build()

                chain.proceed(request.newBuilder().url(url).build())
            }.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        )
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    @Provides
    fun provideService(retrofit: Retrofit): NavitiaService =
        retrofit.create(NavitiaService::class.java)

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun providePlaceRemoteDataSource(navitiaService: NavitiaService): PlaceRemoteDataSource =
        PlaceRemoteDataSource(navitiaService)

    @Singleton
    @Provides
    fun bindRepository(remoteDataSource: PlaceRemoteDataSource) = PlaceRepository(remoteDataSource)
}