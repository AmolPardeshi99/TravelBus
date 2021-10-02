package com.example.travelbus.views.adapter.fragments.bus_hire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.travelbus.R
import kotlinx.android.synthetic.main.fragment_select_journey_type.*

class SelectJourneyTypeFragment : Fragment(R.layout.fragment_select_journey_type) {
    lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        btnGoToNext.setOnClickListener {
            navController.navigate(R.id.action_selectJourneyTypeFragment_to_busHireDetailFragment)
        }


    }
}