package com.example.travelbus.views.adapter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.travelbus.R
import kotlinx.android.synthetic.main.fragment_country__language.*

class Country_LanguageFragment : Fragment(R.layout.fragment_country__language) {
    lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

     btnContinue.setOnClickListener {
         navController.navigate(R.id.action_country_LanguageFragment_to_onBoardingMainFragment)
     }
    }
}