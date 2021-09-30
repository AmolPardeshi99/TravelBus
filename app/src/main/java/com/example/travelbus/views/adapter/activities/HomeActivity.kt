package com.example.travelbus.views.adapter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.travelbus.R
import com.example.travelbus.views.adapter.adapters.PlacesAdapter
import com.example.travelbus.views.adapter.fragments.*
import com.google.android.material.navigation.NavigationBarView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), PlacesAdapter.ClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setBottomNavigation()
    }


    private fun setBottomNavigation() {
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment(this)).commit()

        menu_bottom.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            var temp: Fragment? = null
            when (item.itemId) {
                R.id.menu_home -> temp = HomeFragment(this)
                R.id.menu_bookings -> temp = MyBookingsFragment()
                R.id.menu_help -> temp = HelpFragment()
                R.id.menu_account -> temp = MyAccountFragment()
            }
            if (temp != null) {
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, temp).commit()
            }
            true
        })
    }

    override fun click(area: String?, address: String?) {
        TODO("Not yet implemented")
    }

    override fun boardingItemClicked(boardingPoint: BoardingPoint?) {
        TODO("Not yet implemented")
    }

    override fun cityItemClicked(city: String?) {
        TODO("Not yet implemented")
    }

    override fun sourceSearchClicked() {
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, LocationSearchFragment(this)).addToBackStack("Source Search Fragment").commit()
        menu_bottom.visibility = View.GONE
    }

    override fun destinationSearchClicked() {
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, LocationSearchFragment(this)).addToBackStack("Destination Search Fragment").commit()
        menu_bottom.visibility = View.GONE
    }
}