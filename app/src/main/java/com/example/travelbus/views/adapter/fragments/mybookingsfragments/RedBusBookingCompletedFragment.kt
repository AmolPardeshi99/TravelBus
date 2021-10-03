package com.example.travelbus.views.adapter.fragments.mybookingsfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelbus.R
import com.example.travelbus.models.local.BookingDetails
import com.example.travelbus.views.adapter.adapters.BookingListAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_r_pool_booking_completed.*
import kotlinx.android.synthetic.main.fragment_red_bus_booking_completed.*

class RedBusBookingCompletedFragment : Fragment(R.layout.fragment_red_bus_booking_completed) {

    val bookingList = ArrayList<BookingDetails>()

    private var db = Firebase.firestore
    private lateinit var auth: FirebaseAuth
    var userRef = db.collection("users")
    var bookingRef = db.collection("bookings")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        getFirebaseData()
    }

    private fun getFirebaseData() {
        auth.currentUser?.uid.let { id ->
            if (id != null) {
                userRef.document(id).get().addOnSuccessListener { doc->
                    if (doc.data?.get("bookings") != null) {
                        val bookingId: String =  doc.data?.get("bookings").toString()
                        setAdapter(bookingId)
                    }
                }
            }
        }
    }

    private fun setAdapter(bookingId: String) {
        bookingList.add(BookingDetails("04 Oct 2021, Monday", "Pune", "Mumbai", "272.9kms", "299/- Paid Via Phonepe"))
        val bookingListAdapter = BookingListAdapter(bookingList)
        val linearLayoutManager  = LinearLayoutManager(activity)

        recyclerViewRedBusCompleted.layoutManager = linearLayoutManager
        recyclerViewRedBusCompleted.adapter = bookingListAdapter
        bookingListAdapter.notifyDataSetChanged()
    }

}