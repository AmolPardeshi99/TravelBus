package com.example.travelbus.views.adapter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.travelbus.R
import com.example.travelbus.views.adapter.adapters.PlacesAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment(private val clickListener: PlacesAdapter.ClickListener) : Fragment(R.layout.fragment_home) {

    private var area : String? = ""
    private var address : String? = ""
    private var sourceDestinationFlag : Int? = null
    private var sourceText = ""
    private var destinationText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            area = getString("area").toString()
            address = getString("address").toString()
            sourceDestinationFlag = getInt("flag")
            sourceText = getString("sourceText").toString()
            destinationText = getString("destinationText").toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        etEnterSource.setText(sourceText)
        etEnterDestination.setText(destinationText)

        sourceDestinationFlag.let {
            if (it == 1){
                if (address ==""){
                    etEnterSource.setText(area)
                } else {
                    etEnterSource.setText("$area, $address")
                }
            } else{
                if (address == ""){
                    etEnterDestination.setText(area)
                } else {
                    etEnterDestination.setText("$area, $address")
                }
            }
        }

        etEnterSource.setOnClickListener {
            clickListener.sourceSearchClicked(etEnterSource.text.toString(), etEnterDestination.text.toString())
        }
        etEnterDestination.setOnClickListener {
            clickListener.destinationSearchClicked(etEnterSource.text.toString(), etEnterDestination.text.toString())
        }

        btnSearch.setOnClickListener {

        }
    }
}