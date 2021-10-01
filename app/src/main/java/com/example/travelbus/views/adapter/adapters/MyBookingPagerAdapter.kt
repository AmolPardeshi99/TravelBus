package com.example.travelbus.views.adapter.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.travelbus.views.adapter.fragments.mybookingsfragments.BusHireMyBookingFragment
import com.example.travelbus.views.adapter.fragments.mybookingsfragments.RPoolMyBookingFragment
import com.example.travelbus.views.adapter.fragments.mybookingsfragments.RedBusMyBookingFragment

class MyBookingPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return RedBusMyBookingFragment()
            1 -> return RPoolMyBookingFragment()
            2 -> return BusHireMyBookingFragment()
        }
        return RedBusMyBookingFragment()
    }

    override fun getItemCount(): Int {
        return 3
    }
}