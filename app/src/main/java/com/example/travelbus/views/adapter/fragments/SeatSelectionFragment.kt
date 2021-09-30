package com.example.travelbus.views.adapter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelbus.R
import com.example.travelbus.views.adapter.adapters.BusSeatAdapter
import kotlinx.android.synthetic.main.fragment_seat_selection.*

class SeatSelectionFragment : Fragment(R.layout.fragment_seat_selection) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildSeatLayout()
    }

    private fun buildSeatLayout() {
        val busSeats = ArrayList<BusSeat>()
        for (i in 1..10){
            busSeats.add(BusSeat("Female", "Yes"))
        }
        for (i in 1..10){
            busSeats.add(BusSeat("Female", "No"))
        }
        for (i in 1..10){
            busSeats.add(BusSeat("General", "Yes"))
        }
        for (i in 1..10){
            busSeats.add(BusSeat("General", "No"))
        }
        val seatAdapter = BusSeatAdapter(busSeats)
        val linearLayoutManager = GridLayoutManager(activity, 5)
        recyclerViewBus.adapter = seatAdapter
        recyclerViewBus.layoutManager = linearLayoutManager
    }

}

class BusSeat(val Type: String, val Availability: String) {
}