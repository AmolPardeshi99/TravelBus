package com.example.travelbus.views.adapter.fragments

import android.os.Bundle
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

class PassengerDetailsFragment : Fragment(R.layout.fragment_passenger_details) {
    var db = Firebase.firestore
    var passenRef = db.collection("passenger")
    var userRef = db.collection("users")
    var bookingRef = db.collection("bookings")
    lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth


    var bus_id = ""
    var name = ""
    var phone = ""
    var email = ""
    var age = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        auth = Firebase.auth
        auth.currentUser.toString()
        btnProceed.setOnClickListener {
            navController.navigate(R.id.action_passengerDetailsFragment_to_paymentFragment)
        }

        arguments?.run {
            bus_id = getString("bus_id").toString()
        }

        btnProceed.setOnClickListener {
            name = etName.text.toString()
            age = etAge.text.toString()
            email = etEmail.text.toString()
            phone = etEnterMobile.text.toString()
            auth.currentUser?.uid.let {
                it?.let { it1 -> userRef.document(it1).update("email",email) }
                it?.let { it1 -> userRef.document(it1).update("email",email) }


            }
        }


    }

}