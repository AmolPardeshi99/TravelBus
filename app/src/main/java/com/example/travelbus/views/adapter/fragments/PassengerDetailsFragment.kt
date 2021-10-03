package com.example.travelbus.views.adapter.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.travelbus.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_passenger_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PassengerDetailsFragment : Fragment(R.layout.fragment_passenger_details) {
    private var db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    var userRef = db.collection("users")
    var bookingRef = db.collection("bookings")
    lateinit var navController: NavController


    var bus_id = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        auth = Firebase.auth

        btnProceed.setOnClickListener {
            getBookingId()
        }

        arguments?.run {
            bus_id = getString("bus_id").toString()
            Log.d("abhishek", bus_id)
        }
    }

    private fun getBookingId() {
        auth.currentUser?.uid.let { id ->
            if (id != null) {
                userRef.document(id).get().addOnSuccessListener { doc->
                    if (doc.data?.get("bookings") != null) {
                        val bookingId: String =  doc.data?.get("bookings").toString()
                        setBookingsData(bookingId)
                    }
                }
            }
        }
    }

    private fun setBookingsData(bookingId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            var name = ""
            var age = ""
            var email = ""
            var phone = ""

            name = etPassengerName.text.toString()
            age = etPassengerAge.text.toString()
            email = etPassengerEmail.text.toString()
            phone = etEnterPassengerMobile.text.toString()

            bookingRef.document(bookingId).update("bus_id" , bus_id)
            bookingRef.document(bookingId).update("email" , email)
            bookingRef.document(bookingId).update("mobile" , phone)

            val data = hashMapOf(
                "name" to name,
                "age" to age
            )
            bookingRef.document(bookingId).collection("passenger").add(data)

            navController.navigate(R.id.action_passengerDetailsFragment_to_paymentFragment)
        }

    }

}