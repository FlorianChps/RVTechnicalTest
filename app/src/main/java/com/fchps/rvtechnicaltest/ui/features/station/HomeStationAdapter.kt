package com.fchps.rvtechnicaltest.ui.features.station

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.fchps.rvtechnicaltest.R
import com.fchps.rvtechnicaltest.data.entities.Place
import com.fchps.rvtechnicaltest.utils.inflate

class HomeStationAdapter : ListAdapter<Place, StationViewHolder>(StationDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StationViewHolder(parent.inflate(R.layout.item_home_station))

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class StationDiffUtil : DiffUtil.ItemCallback<Place>() {
        override fun areItemsTheSame(oldItem: Place, newItem: Place) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Place, newItem: Place) = oldItem == newItem
    }
}

