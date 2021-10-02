package com.example.travelbus.views.adapter.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.travelbus.R
import com.example.travelbus.views.adapter.activities.BusBookingActivity
import com.example.travelbus.views.adapter.activities.BusHireActivity
import com.example.travelbus.views.adapter.adapters.PlacesAdapter
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment(private val clickListener: PlacesAdapter.ClickListener) : Fragment(R.layout.fragment_home) {

    private var area : String? = ""
    private var address : String? = ""
    private var sourceDestinationFlag : Int? = null
    private var sourceText = ""
    private var destinationText = ""
    private val db = Firebase.firestore
    private val bookingRef = db.collection("bookings")


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
            var bookingModel = BookingModel()
            bookingModel.date = etDate.text.toString()
            bookingModel.to = etEnterDestination.text.toString()
            bookingModel.from = etEnterSource.text.toString()

            val id = bookingRef.document().id
            bookingModel.booking_Id = id
            bookingModel.bus_Id = ""
            bookingRef.document(id).set(bookingModel)
            val intent = Intent(context,BusBookingActivity::class.java)
            startActivity(intent)
        }


        // Bus Hire
        cvBusHire.setOnClickListener {
            startActivity(Intent(context,BusHireActivity::class.java))
        }
    }

    class BookingModel(
        var date:String="",
        var from:String="",
        var to:String="",
        var booking_Id:String="",
        var bus_Id:String=""
    ){

    }

}