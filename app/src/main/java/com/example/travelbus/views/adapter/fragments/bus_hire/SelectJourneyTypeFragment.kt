package com.example.travelbus.views.adapter.fragments.bus_hire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.travelbus.R
import kotlinx.android.synthetic.main.fragment_select_journey_type.*

class SelectJourneyTypeFragment : Fragment(R.layout.fragment_select_journey_type) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageOutstaion.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.busHireContainer, BusHireDetailFragment())?.commit()
        }
        imageLocal.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.busHireContainer, BusHireDetailFragment())?.commit()
        }
        imageAirport.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.busHireContainer, BusHireDetailFragment())?.commit()
        }
    }
}