package com.example.travelbus.views.adapter.fragments.mybookingsfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.travelbus.R
import com.example.travelbus.views.adapter.adapters.BusHireBookingPagerAdapter
import com.example.travelbus.views.adapter.adapters.RPoolBookingPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_bus_hire_my_booking.*
import kotlinx.android.synthetic.main.fragment_r_pool_my_booking.*


class BusHireMyBookingFragment :Fragment(R.layout.fragment_bus_hire_my_booking) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        val pagerAdapter =
            BusHireBookingPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        viewPagerBusHire.adapter = pagerAdapter
        TabLayoutMediator(tabsBusHire, viewPagerBusHire) { tab, position ->
            when (position) {
                0 -> tab.text = "COMPLETED"
                1 -> tab.text = "CANCELLED"
            }
        }.attach()
    }
}