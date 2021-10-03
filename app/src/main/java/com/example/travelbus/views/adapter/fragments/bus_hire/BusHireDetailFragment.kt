package com.example.travelbus.views.adapter.fragments.bus_hire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.travelbus.R
import kotlinx.android.synthetic.main.fragment_bus_hire_detail.*
import kotlinx.android.synthetic.main.fragment_bus_hire_welcome.view.*
import android.content.res.ColorStateList


class BusHireDetailFragment : Fragment(R.layout.fragment_bus_hire_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radioButtonColorChange()

        btnbhProceed.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.example.travelbus.R.id.busHireContainer, FillContactDetailFragment())
                ?.commit()
        }

    }

    private fun radioButtonColorChange() {
        val colorStateList = ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_enabled)), intArrayOf(
                resources.getColor(com.example.travelbus.R.color.materialRed)
            )
        )

        radioButton.setOnClickListener {
            radioButton.buttonTintList = colorStateList
        }
        radioButton4.setOnClickListener {
            radioButton4.buttonTintList = colorStateList
        }
        radioButton2.setOnClickListener {
            radioButton2.buttonTintList = colorStateList
        }
        radioButton3.setOnClickListener {
            radioButton3.buttonTintList = colorStateList
        }
    }
}