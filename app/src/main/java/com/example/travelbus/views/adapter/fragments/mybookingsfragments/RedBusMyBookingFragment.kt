package com.example.travelbus.views.adapter.fragments.mybookingsfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.travelbus.R
import com.example.travelbus.views.adapter.adapters.MyBookingPagerAdapter
import com.example.travelbus.views.adapter.adapters.RedBusBookingPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_my_bookings.*
import kotlinx.android.synthetic.main.fragment_red_bus_my_booking.*

class RedBusMyBookingFragment : Fragment(R.layout.fragment_red_bus_my_booking) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        val pagerAdapter =
            RedBusBookingPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        viewPagerRedBus.adapter = pagerAdapter
        TabLayoutMediator(tabsRedBus, viewPagerRedBus) { tab, position ->
            when (position) {
                0 -> tab.text = "COMPLETED"
                1 -> tab.text = "CANCELLED"
            }
        }.attach()
    }

}