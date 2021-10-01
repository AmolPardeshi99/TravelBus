package com.example.travelbus.views.adapter.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.travelbus.R
import com.example.travelbus.models.local.Seats
import com.example.travelbus.views.adapter.adapters.BusSeatAdapter
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_seat_selection.*

class SeatSelectionFragment : Fragment(R.layout.fragment_seat_selection) {
lateinit var navController: NavController
    private val db = Firebase.firestore
    private val busRef = db.collection("Buses")
    private var listOfSeats= ArrayList<Seats>()
    lateinit var busSeatAdapter:BusSeatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        busSeatAdapter = BusSeatAdapter(listOfSeats)
        navController = Navigation.findNavController(view)
        getBusesData()
        btnSignupFrag.setOnClickListener {
            navController.navigate(R.id.action_seatSelectionFragment_to_passengerDetailsFragment)
        }
        setRecyclerView()
    }

    private fun setRecyclerView() {
        busSeatAdapter = BusSeatAdapter(listOfSeats)
        recyclerViewBus.layoutManager = GridLayoutManager(context,5)
        recyclerViewBus.adapter = busSeatAdapter

    }

    private fun getBusesData() {
        busRef.document("4YAfjOnotl9HL5AGzJWL").get().addOnSuccessListener {doc->
            if (doc!=null){

                var seats = doc.data?.get("seats") as ArrayList<HashMap<String,String>>



                for (i in 0 until seats.size) {
                    var seatmodel = Seats()
                    seatmodel.available = seats[i].getValue("available")
                    seatmodel.real_seat = seats[i].getValue("real_seat")
                    seatmodel.type = seats[i].getValue("type")
                    seatmodel.seat_no = seats[i].getValue("seat_no")
                    listOfSeats.add(seatmodel)
                    Log.d("TAG", "getBusesData: ${seatmodel.toString()}")
                }
                busSeatAdapter.notifyDataSetChanged()
            }

        }
    }


}

