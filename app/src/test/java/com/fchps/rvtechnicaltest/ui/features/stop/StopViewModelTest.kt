package com.fchps.rvtechnicaltest.ui.features.stop

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fchps.RxImmediateSchedulerRule
import com.fchps.rvtechnicaltest.data.repository.StopRepository
import com.fchps.rvtechnicaltest.ui.features.details.StopDetailsModel
import com.fchps.rvtechnicaltest.ui.features.details.StopState
import com.fchps.rvtechnicaltest.ui.features.details.StopViewModel
import com.fchps.rvtechnicaltest.ui.features.details.TransportType
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
class StopViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    lateinit var stopViewModel: StopViewModel

    @MockK
    lateinit var repository: StopRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        stopViewModel = StopViewModel(repository)
    }

    @Test
    fun `Test success case`() {
        // Given
        val id = "fakeId"
        val localResponse = listOf(
            StopDetailsModel(
                TransportType.BUS,
                "#FFFFFF",
                "12",
                "14:45",
                "Destination de test "
            )
        )

        // When
        every { repository.getStopDetails(id) } returns Observable.just(localResponse)
        // Then
        stopViewModel.getDetails(id)

        assertNotNull(stopViewModel.stopLiveData.value)
        assertEquals(stopViewModel.stopLiveData.value, StopState.Success(localResponse))
    }
}