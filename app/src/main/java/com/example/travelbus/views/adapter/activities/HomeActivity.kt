package com.example.travelbus.views.adapter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.travelbus.R
import com.example.travelbus.views.adapter.fragments.HelpFragment
import com.example.travelbus.views.adapter.fragments.HomeFragment
import com.example.travelbus.views.adapter.fragments.MyAccountFragment
import com.example.travelbus.views.adapter.fragments.MyBookingsFragment
import com.google.android.material.navigation.NavigationBarView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setBottomNavigation()
    }


    private fun setBottomNavigation() {
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment()).commit()

        menu_bottom.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            var temp: Fragment? = null
            when (item.itemId) {
                R.id.menu_home -> temp = HomeFragment()
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
}