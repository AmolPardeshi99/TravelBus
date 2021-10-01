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

    private var sourceText = ""
    private var destinationText = ""

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

    override fun click(area: String?, address: String?, flag: Int) {
        val fragment = HomeFragment(this)
        val bundle = Bundle()
        bundle.putInt("flag", flag)
        bundle.putString("area", area)
        bundle.putString("address", address)
        bundle.putString("sourceText", sourceText)
        bundle.putString("destinationText", destinationText)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
        menu_bottom.visibility = View.VISIBLE
    }

    override fun boardingItemClicked(boardingPoint: BoardingPoint?, flag: Int) {
        val fragment = HomeFragment(this)
        val bundle = Bundle()
        bundle.putInt("flag", flag)
        bundle.putString("area", boardingPoint?.area)
        bundle.putString("address", boardingPoint?.address)
        bundle.putString("sourceText", sourceText)
        bundle.putString("destinationText", destinationText)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
        menu_bottom.visibility = View.VISIBLE
    }

    override fun cityItemClicked(city: String?, flag: Int) {
        val fragment = HomeFragment(this)
        val bundle = Bundle()
        bundle.putInt("flag", flag)
        bundle.putString("area", city)
        bundle.putString("address", "")
        bundle.putString("sourceText", sourceText)
        bundle.putString("destinationText", destinationText)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
        menu_bottom.visibility = View.VISIBLE
    }

    override fun sourceSearchClicked(sourceText : String, destinationText : String) {
        this.sourceText = sourceText
        this.destinationText = destinationText
        val fragment = LocationSearchFragment(this)
        val bundle = Bundle()
        bundle.putInt("flag", 1)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, fragment).addToBackStack("Source Search Fragment").commit()
        menu_bottom.visibility = View.GONE
    }

    override fun destinationSearchClicked(sourceText : String, destinationText : String) {
        this.sourceText = sourceText
        this.destinationText = destinationText
        val fragment = LocationSearchFragment(this)
        val bundle = Bundle()
        bundle.putInt("flag", 2)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, fragment).addToBackStack("Source Search Fragment").commit()
        menu_bottom.visibility = View.GONE
    }
}