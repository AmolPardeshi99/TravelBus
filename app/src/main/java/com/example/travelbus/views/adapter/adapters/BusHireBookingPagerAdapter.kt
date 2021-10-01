package com.example.travelbus.views.adapter.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.travelbus.views.adapter.fragments.mybookingsfragments.*

class BusHireBookingPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return BusHireBookingCompletedFragment()
            1 -> return BusHireBookingCancelledFragment()
        }
        return BusHireBookingCompletedFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}