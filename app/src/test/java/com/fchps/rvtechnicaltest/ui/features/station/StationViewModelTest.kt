package com.fchps.rvtechnicaltest.ui.features.station

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.fchps.RxImmediateSchedulerRule
import com.fchps.rvtechnicaltest.data.repository.PlaceRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class StationViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    lateinit var stationViewModel: StationViewModel

    @MockK
    lateinit var repository: PlaceRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        stationViewModel = StationViewModel(repository)
    }

    @Test
    fun `Test success case`() {
        // Given
        val searchString = "test"
        val localResponse = listOf(PlaceModel("id1", "name1", false))

        // When
        every { repository.getPlaces(searchString) } returns Observable.just(localResponse)
        // Then
        stationViewModel.getPlaces(searchString)

        assertNotNull(stationViewModel.stationLiveData.value)
        assertEquals(
            stationViewModel.stationLiveData.value,
            StationState.Success(localResponse)
        )
    }

}