package com.fchps.rvtechnicaltest.ui.features.details

import android.graphics.Color
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.fchps.rvtechnicaltest.R

class StopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.details_stop_destination)
    private val departureTime: TextView = itemView.findViewById(R.id.time_details_stop)
    private val label: TextView = itemView.findViewById(R.id.details_stop_number_commercial_mode)
    private val mode: ImageView = itemView.findViewById(R.id.details_stop_image_commercial_mode)

    @RequiresApi(Build.VERSION_CODES.Q)
    fun bind(stop: StopDetailsModel) {
        departureTime.text = stop.departureHour
        mode.setImageResource(stop.type.resId)
        label.background.setTint(Color.parseColor(stop.color))
        label.text = stop.code
        name.text = stop.destination
    }
}