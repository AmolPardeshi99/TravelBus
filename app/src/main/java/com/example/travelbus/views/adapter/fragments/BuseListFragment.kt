package com.example.travelbus.views.adapter.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.travelbus.R
import com.example.travelbus.models.local.Buses
import com.example.travelbus.views.adapter.adapters.BusAdapter
import com.example.travelbus.views.adapter.adapters.OnBusItemClickListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_buse_list.*

class BuseListFragment : Fragment(R.layout.fragment_buse_list),OnBusItemClickListener {
    private val db = Firebase.firestore
    private val busRef = db.collection("Buses");
    private var listOfBuses = ArrayList<Buses>()
    val TAG = "Travel APP"
    private lateinit var busAdapter:BusAdapter
    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        busAdapter = BusAdapter(listOfBuses,this)
        getAllStore()
        recyclerviewBus.adapter = busAdapter
        navController = Navigation.findNavController(view)
    }
    private fun getAllStore() {
        listOfBuses.clear()
         busRef.addSnapshotListener { snapshot, e ->
            if (snapshot != null && !snapshot.isEmpty) {
                //tvStoreSize.text = snapshot.size().toString() + " Stores"
                for (doc in snapshot) {
                    Log.d(TAG, doc.data["address"].toString())

                    var storeListObject = doc.toObject(Buses::class.java)

                    listOfBuses.add(storeListObject)
                    Log.d(TAG, "Current data: ${doc.data}")

                }
                busAdapter.notifyDataSetChanged()

                Log.d(TAG, "storeList: ${listOfBuses}")
                Log.d(TAG, "storeList: ${listOfBuses[0].description}")

            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }

    override fun onBusClicked(buses: Buses) {
        navController.navigate(R.id.action_buseListFragment_to_seatSelectionFragment)
    }


}