package com.example.travelbus.views.adapter.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.travelbus.views.adapter.fragments.mybookingsfragments.*

class RedBusBookingPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return RedBusBookingCompletedFragment()
            1 -> return RedBusBookingCancelledFragment()
        }
        return RedBusMyBookingFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}