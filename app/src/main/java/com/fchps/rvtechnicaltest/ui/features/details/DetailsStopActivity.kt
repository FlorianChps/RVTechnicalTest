package com.fchps.rvtechnicaltest.ui.features.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fchps.rvtechnicaltest.R
import com.fchps.rvtechnicaltest.data.entities.Place
import com.fchps.rvtechnicaltest.databinding.ActivityDetailsStopBinding

class DetailsStopActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsStopBinding
    private val place: Place? get() = intent.getParcelableExtra(PLACE_ARG)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_stop)
        binding.lifecycleOwner = this

        place?.let {
            binding.detailsStopToolbar.setNavigationOnClickListener { onBackPressed() }
            binding.detailsStopTitle.text = it.name
            binding.idStation.text = "${it.name} ${it.id}"
        }
    }


    companion object {
        private const val PLACE_ARG = "PLACE_ARG"

        fun navigateTo(context: Context, place: Place) =
            Intent(context, DetailsStopActivity::class.java).apply {
                putExtra(PLACE_ARG, place)
            }
    }
}