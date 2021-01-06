package com.fchps.rvtechnicaltest.ui.features.station

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fchps.rvtechnicaltest.R

class StationViewHolder(
    itemView: View,
    val onStationClicked: (PlaceModel) -> Unit,
    val onFavoritedChecked: (PlaceModel) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.station_title)
    private val isFavorite: ImageView = itemView.findViewById(R.id.station_favorite)

    fun bind(place: PlaceModel) {
        isFavorite.setImageResource(if (place.isFavorite) R.drawable.ic_star_fill else R.drawable.ic_star_empty)
        itemView.setOnClickListener { onStationClicked(place) }
        isFavorite.setOnClickListener {
            if (place.isFavorite) {
                isFavorite.setImageResource(R.drawable.ic_star_empty)
                place.isFavorite = false
                onFavoritedChecked(place)
            } else {
                isFavorite.setImageResource(R.drawable.ic_star_fill)
                place.isFavorite = true
                onFavoritedChecked(place)
            }
        }
        title.text = place.name
    }
}