package com.fchps.rvtechnicaltest.ui.features.station

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.fchps.rvtechnicaltest.R
import com.fchps.rvtechnicaltest.utils.inflate

class HomeStationAdapter(
    private val onStationClicked: (PlaceModel) -> Unit,
) : ListAdapter<PlaceModel, StationViewHolder>(StationDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StationViewHolder(parent.inflate(R.layout.item_home_station)) { position ->
            onStationClicked(getItem(position) as PlaceModel)
        }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class StationDiffUtil : DiffUtil.ItemCallback<PlaceModel>() {
        override fun areItemsTheSame(oldItem: PlaceModel, newItem: PlaceModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PlaceModel, newItem: PlaceModel) =
            oldItem == newItem
    }
}

