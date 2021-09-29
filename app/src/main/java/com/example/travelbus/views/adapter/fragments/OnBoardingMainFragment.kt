package com.example.travelbus.views.adapter.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.travelbus.R
import com.example.travelbus.views.adapter.adapters.OnBoardingFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import kotlinx.android.synthetic.main.fragment_on_boarding_main.*

class OnBoardingMainFragment : Fragment(R.layout.fragment_on_boarding_main), TabLayout.OnTabSelectedListener {
    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPagerAdapter()
        navController = Navigation.findNavController(view)

        // navigation for next fragment
        btnJoin.setOnClickListener {
            navController.navigate(R.id.action_onBoardingMainFragment_to_signupOrLoginFragment)

        }
    }


    private fun setViewPagerAdapter() {
        val viewPagerAdapter = OnBoardingFragmentAdapter(activity as Context)
        viewPager.setAdapter(viewPagerAdapter)
        tabLayout.setupWithViewPager(viewPager)

//        TabLayoutMediator(
//            tabLayout, viewPager
//        ) { tab, position -> }.attach()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        TODO("Not yet implemented")
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        TODO("Not yet implemented")
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        TODO("Not yet implemented")
    }

}