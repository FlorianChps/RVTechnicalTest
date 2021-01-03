package com.fchps.rvtechnicaltest.ui.features.station

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fchps.rvtechnicaltest.R
import com.fchps.rvtechnicaltest.data.entities.Place

class StationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.station_title)

    fun bind(place: Place) {
        title.text = place.name
    }
}