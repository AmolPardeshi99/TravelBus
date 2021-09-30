package com.example.travelbus.views.adapter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.travelbus.R
import com.example.travelbus.views.adapter.adapters.PlacesAdapter

class HomeFragment(private val clickListener: PlacesAdapter.ClickListener) : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        clickListener.sourceSearchClicked()
        clickListener.destinationSearchClicked()
    }
}