package com.example.travelbus.views.adapter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.travelbus.R
import com.example.travelbus.views.adapter.adapters.MyBookingPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import kotlinx.android.synthetic.main.fragment_my_bookings.*

class MyBookingsFragment : Fragment(R.layout.fragment_my_bookings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        val pagerAdapter = MyBookingPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        viewPagerBookingType.adapter = pagerAdapter
        TabLayoutMediator(tabsMyBooking, viewPagerBookingType) {
                tab, position ->
            when (position) {
                0 -> tab.text = "redBus"
                1 -> tab.text = "rPool"
                2 -> tab.text = "Bus Hire"
            }
        }.attach()
    }
}