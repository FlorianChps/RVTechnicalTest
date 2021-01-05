package com.fchps.rvtechnicaltest.ui.features.station

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fchps.rvtechnicaltest.R
import com.fchps.rvtechnicaltest.utils.doSafelyOnItemClicked

class StationViewHolder(
    itemView: View,
    onStationClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    init {
        doSafelyOnItemClicked(onStationClicked)
    }

    private val title: TextView = itemView.findViewById(R.id.station_title)
    private val isFavorite: ImageView = itemView.findViewById(R.id.station_favorite)

    fun bind(place: PlaceModel) {
        isFavorite.setOnClickListener {
            if (place.isFavorite) {
                isFavorite.setImageResource(R.drawable.ic_star_empty)
                place.isFavorite = false
            } else {
                isFavorite.setImageResource(R.drawable.ic_star_fill)
                place.isFavorite = true
            }
        }
        title.text = place.name
    }
}