package com.fchps.rvtechnicaltest.ui.features.station

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.fchps.rvtechnicaltest.R
import com.fchps.rvtechnicaltest.data.entities.Place
import com.fchps.rvtechnicaltest.databinding.ActivityMainBinding
import com.fchps.rvtechnicaltest.ui.features.details.DetailsStopActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var stationAdapter: HomeStationAdapter
    private val viewModel: StationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        initStationRecyclerView()
        initSearchView()
        observeStations()
    }

    private fun initSearchView() {
        binding.homeSearchStation.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return handleSearchQuery(query)
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return handleSearchQuery(newText)
            }
        })
    }

    private fun handleSearchQuery(query: String?): Boolean {
        if (query.isNullOrEmpty()) {
            stationAdapter.submitList(null)
        } else {
            viewModel.getPlaces(query)
        }
        return true
    }

    private fun observeStations() {
        viewModel.stationLiveData.observe(this, { list ->
            stationAdapter.submitList(list)
        })
    }

    private fun initStationRecyclerView() {
        binding.homeStationList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            val dividerItemDecoration = DividerItemDecoration(context, VERTICAL)
            ResourcesCompat.getDrawable(resources, R.drawable.divider, theme)
                ?.let { dividerItemDecoration.setDrawable(it) }
            addItemDecoration(dividerItemDecoration)
            stationAdapter = HomeStationAdapter(::onStationClicked)
            adapter = stationAdapter
        }
    }

    private fun onStationClicked(place: PlaceModel) {
        startActivity(DetailsStopActivity.navigateTo(this, place))
    }
}