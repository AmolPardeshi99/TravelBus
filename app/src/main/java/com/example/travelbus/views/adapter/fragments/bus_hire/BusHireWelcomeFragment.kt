package com.example.travelbus.views.adapter.fragments.bus_hire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.travelbus.R
import kotlinx.android.synthetic.main.fragment_bus_hire_welcome.*

class BusHireWelcomeFragment : Fragment(R.layout.fragment_bus_hire_welcome) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnHireVehicle1.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.busHireContainer, SelectJourneyTypeFragment())?.commit()
        }
        btnHireVehicle2.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.busHireContainer, SelectJourneyTypeFragment())?.commit()

        }

    }

}