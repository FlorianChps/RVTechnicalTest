package com.fchps.rvtechnicaltest.ui.features.station

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.fchps.rvtechnicaltest.R
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
        viewModel.getLocalPlaces()
    }

    private fun initSearchView() {
        binding.homeSearchStation.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = handleSearchQuery(query)
            override fun onQueryTextChange(newText: String?) = handleSearchQuery(newText)
        })
    }

    private fun handleSearchQuery(query: String?): Boolean {
        if (query.isNullOrEmpty()) {
            viewModel.getLocalPlaces()
        } else {
            viewModel.getPlaces(query)
        }
        return false
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
            stationAdapter = HomeStationAdapter(::onStationClicked, ::onFavoriteChecked)
            adapter = stationAdapter
        }
    }

    private fun onStationClicked(place: PlaceModel) {
        startActivity(DetailsStopActivity.navigateTo(this, place))
    }

    private fun onFavoriteChecked(placeModel: PlaceModel) {
        if (placeModel.isFavorite) {
            viewModel.savePlace(placeModel)
        } else {
            viewModel.deletePlace(placeModel)
        }
        if (binding.homeSearchStation.query.isNullOrEmpty()) {
            return
        } else {
            viewModel.getPlaces(binding.homeSearchStation.query.toString())
        }
    }
}