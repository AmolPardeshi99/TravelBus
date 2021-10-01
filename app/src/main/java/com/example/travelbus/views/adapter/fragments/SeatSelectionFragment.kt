package com.example.travelbus.views.adapter.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.travelbus.R
import com.example.travelbus.models.local.Seats
import com.example.travelbus.views.adapter.adapters.BusSeatAdapter
import com.example.travelbus.views.adapter.adapters.SeatClickedListener
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_seat_selection.*

class SeatSelectionFragment : Fragment(R.layout.fragment_seat_selection), SeatClickedListener {
    lateinit var navController: NavController
    private val db = Firebase.firestore
    private val busRef = db.collection("Buses")
    private var listOfSeats = ArrayList<Seats>()
    lateinit var busSeatAdapter: BusSeatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        busSeatAdapter = BusSeatAdapter(listOfSeats, this)
        navController = Navigation.findNavController(view)
        getBusesData()
        btnSignupFrag.setOnClickListener {
            navController.navigate(R.id.action_seatSelectionFragment_to_passengerDetailsFragment)
        }
        setRecyclerView()
        recyclerViewBus.setOnClickListener{
            Toast.makeText(activity, "RV Clicked!", Toast.LENGTH_SHORT).show()
        }
    }



    private fun setRecyclerView() {
        recyclerViewBus.layoutManager = GridLayoutManager(context, 5)
        recyclerViewBus.adapter = busSeatAdapter
    }

    private fun getBusesData() {
        busRef.document("4YAfjOnotl9HL5AGzJWL").get().addOnSuccessListener { doc ->
            if (doc != null) {
                var seats = doc.data?.get("seats") as ArrayList<HashMap<String, String>>

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

    override fun onSeatSelected() {
        btnAllPrices.setTextColor(ContextCompat.getColor(activity?.applicationContext!!, R.color.black))
        btnAllPrices.setBackgroundColor(ContextCompat.getColor(activity?.applicationContext!!, R.color.white))
        btnAllPrices.setStrokeColorResource(R.color.grey)
        btn299.setBackgroundColor(ContextCompat.getColor(activity?.applicationContext!!, R.color.materialRed))
        btn299.setTextColor(ContextCompat.getColor(activity?.applicationContext!!, R.color.white))
        btn299.setStrokeColorResource(R.color.materialRed)

        btnSignupFrag.visibility = View.VISIBLE
    }

    override fun onSeatDeselected() {
        btnSignupFrag.visibility = View.GONE
    }

}

