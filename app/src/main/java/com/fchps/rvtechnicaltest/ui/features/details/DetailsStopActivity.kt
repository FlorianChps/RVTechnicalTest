package com.fchps.rvtechnicaltest.ui.features.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.fchps.rvtechnicaltest.R
import com.fchps.rvtechnicaltest.data.entities.Place
import com.fchps.rvtechnicaltest.databinding.ActivityDetailsStopBinding
import com.fchps.rvtechnicaltest.ui.LastItemSpaceDecorator
import com.fchps.rvtechnicaltest.ui.features.station.PlaceModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsStopActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsStopBinding
    private lateinit var stopsAdapter: DetailStopAdapter
    private val stopViewModel: StopViewModel by viewModels()

    private val place: PlaceModel? get() = intent.getParcelableExtra(PLACE_ARG)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_stop)
        binding.lifecycleOwner = this

        initStopDetailsRecyclerView()
        observeStops()
        place?.let {
            binding.detailsStopToolbar.setNavigationOnClickListener { onBackPressed() }
            binding.detailsStopTitle.text = it.name
            stopViewModel.getDetails(it.id)
        }
    }

    private fun observeStops() {
        stopViewModel.stopLiveData.observe(this, { list ->
            stopsAdapter.submitList(list)
        })
    }

    private fun initStopDetailsRecyclerView() {
        binding.detailsStopList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(LastItemSpaceDecorator())
            stopsAdapter = DetailStopAdapter()
            adapter = stopsAdapter
        }
    }


    companion object {
        private const val PLACE_ARG = "PLACE_ARG"

        fun navigateTo(context: Context, place: PlaceModel) =
            Intent(context, DetailsStopActivity::class.java).apply {
                putExtra(PLACE_ARG, place)
            }
    }
}