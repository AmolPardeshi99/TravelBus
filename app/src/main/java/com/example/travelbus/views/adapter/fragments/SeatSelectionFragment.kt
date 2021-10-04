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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_buse_list.*
import kotlinx.android.synthetic.main.fragment_seat_selection.*

class SeatSelectionFragment : Fragment(R.layout.fragment_seat_selection), SeatClickedListener {
    lateinit var navController: NavController

    private var listOfSeats = ArrayList<Seats>()
    lateinit var busSeatAdapter: BusSeatAdapter

    private val db = Firebase.firestore
    private val busRef = db.collection("Buses")
    private val bookingRef = db.collection("bookings")
    private val userRef = db.collection("users")
    private lateinit var auth: FirebaseAuth
//    lateinit var bookingRefListener: ListenerRegistration
//    lateinit var userRefListener: ListenerRegistration


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        busSeatAdapter = BusSeatAdapter(listOfSeats, this)
        navController = Navigation.findNavController(view)
        getBusesData()

        setRecyclerView()
        recyclerViewBus.setOnClickListener{
            Toast.makeText(activity, "RV Clicked!", Toast.LENGTH_SHORT).show()
        }

        btnSignupFrag.setOnClickListener {
            navController.navigate(R.id.action_seatSelectionFragment_to_passengerDetailsFragment)
        }

    }

//    private fun setBusData() {
//        auth.currentUser?.uid.let { id ->
//            if (id != null) {
//                userRefListener =  userRef.document(id).addSnapshotListener() { snapshot, e ->
//                    if (snapshot != null && snapshot.exists()) {
//                        var bookingId = snapshot.data?.get("bookings")
//                        bookingRefListener = bookingRef.document(bookingId.toString()).addSnapshotListener() { snapshotBooking, ec ->
//                            val date = snapshotBooking?.data?.get("date")
//                            val busId = snapshotBooking?.data?.get("bus_Id")
//                            Log.d("abhishek", "$date $busId")
//                            busRef.document(busId.toString()).addSnapshotListener() { busSnapshot, err ->
//                                val name = busSnapshot?.data?.get("name")
//                                Log.d("abhi", name.toString())
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }


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

