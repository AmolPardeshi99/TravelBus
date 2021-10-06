package com.example.travelbus.views.adapter.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.travelbus.R
import com.example.travelbus.models.local.Buses
import com.example.travelbus.views.adapter.activities.BusBookingActivity
import com.example.travelbus.views.adapter.activities.HomeActivity
import com.example.travelbus.views.adapter.adapters.BusAdapter
import com.example.travelbus.views.adapter.adapters.OnBusItemClickListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.util.Listener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_buse_list.*

class BuseListFragment : Fragment(R.layout.fragment_buse_list), OnBusItemClickListener {
    private val db = Firebase.firestore
    private val busRef = db.collection("Buses")
    private val bookingRef = db.collection("bookings")
    private val userRef = db.collection("users")
    private lateinit var auth: FirebaseAuth

    private var listOfBuses = ArrayList<Buses>()
    val TAG = "Travel APP"
    private lateinit var busAdapter: BusAdapter
    lateinit var navController: NavController
    lateinit var bookingRefListener: ListenerRegistration
    lateinit var userRefListener: ListenerRegistration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllStore()
        busAdapter = BusAdapter(listOfBuses, this)
        recyclerviewBus.adapter = busAdapter
        navController = Navigation.findNavController(view)
    }

//    private fun setToolbarName() {
//        auth.currentUser?.uid.let { id ->
//            if (id != null) {
//                userRefListener = userRef.document(id).addSnapshotListener() { snapshot, e ->
//                    if (snapshot != null && snapshot.exists()) {
//                        var bookingId = snapshot.data?.get("bookings")
//                        bookingRefListener = bookingRef.document(bookingId.toString()).addSnapshotListener() { snapshotBooking, e ->
//                            val from = snapshotBooking?.data?.get("from")
//                            val to = snapshotBooking?.data?.get("to")
//                            tvBusListHeading.setText("$from to $to")
//                        }
//                    }
//                }
//            }
//        }
//    }

    private fun getAllStore() {
        listOfBuses.clear()
        busRef.addSnapshotListener { snapshot, e ->
            if (snapshot != null && !snapshot.isEmpty) {
                //tvStoreSize.text = snapshot.size().toString() + " Stores"
                for (doc in snapshot) {
                    Log.d(TAG, doc.data["address"].toString())
                    var storeListObject = doc.toObject(Buses::class.java)
                    listOfBuses.add(storeListObject)
                }
                busAdapter.notifyDataSetChanged()

            } else {
            }
        }
    }

    override fun onBusClicked(buses: Buses) {
//        userRefListener.remove()
//        bookingRefListener.remove()

        auth.currentUser?.uid.let { id ->
            if (id != null) {
                userRef.document(id).get().addOnSuccessListener { doc ->
                    if (doc.data?.get("bookings") != null) {
                        var bookingId: String = doc.data?.get("bookings").toString()
                        bookingRef.document(bookingId).update("bus_Id", buses.id)
                    }
                }
            }
        }
        navController.navigate(R.id.action_buseListFragment_to_seatSelectionFragment)
    }


}