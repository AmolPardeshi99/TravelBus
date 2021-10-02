package com.example.travelbus.views.adapter.fragments.bus_hire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.travelbus.R
import kotlinx.android.synthetic.main.fragment_bus_hire_detail.*

class BusHireDetailFragment : Fragment(R.layout.fragment_bus_hire_detail) {
    lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        navController = Navigation.findNavController(view)

        btnbhProceed.setOnClickListener {
            navController.navigate(R.id.action_busHireDetailFragment_to_fillContactDetailFragment)
        }
    }

}