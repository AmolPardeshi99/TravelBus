package com.example.travelbus.views.adapter.fragments.bus_hire

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.travelbus.R
import kotlinx.android.synthetic.main.fragment_bus_quotations.*

class BusQuotationsFragment : Fragment(R.layout.fragment_bus_quotations) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickableActions()
    }

    private fun clickableActions() {
        imageQuote1.setOnClickListener {
            imageSelectAppointment.visibility = View.VISIBLE
            imageAppointmentSelected.visibility = View.GONE
        }

        imageSelectAppointment.setOnClickListener {
            imageSelectAppointment.visibility = View.GONE
            imageAppointmentSelected.visibility = View.VISIBLE
        }

        scrollConstraintLayout.setOnClickListener {
            imageSelectAppointment.visibility = View.GONE
            imageAppointmentSelected.visibility = View.GONE
        }

    }

}