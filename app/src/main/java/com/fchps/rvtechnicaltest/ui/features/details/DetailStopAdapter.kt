package com.fchps.rvtechnicaltest.ui.features.details

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.fchps.rvtechnicaltest.R
import com.fchps.rvtechnicaltest.utils.inflate

class DetailStopAdapter : ListAdapter<StopDetailsModel, StopViewHolder>(StopDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StopViewHolder(parent.inflate(R.layout.item_details_stop))

    override fun onBindViewHolder(holder: StopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class StopDiffUtil : DiffUtil.ItemCallback<StopDetailsModel>() {
        override fun areItemsTheSame(oldItem: StopDetailsModel, newItem: StopDetailsModel) =
            oldItem.destination == newItem.destination

        override fun areContentsTheSame(oldItem: StopDetailsModel, newItem: StopDetailsModel) =
            oldItem == newItem
    }
}