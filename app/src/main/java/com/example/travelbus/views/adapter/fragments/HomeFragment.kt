package com.example.travelbus.views.adapter.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.example.travelbus.R
import com.example.travelbus.models.local.BookingDetails
import com.example.travelbus.views.adapter.activities.BusBookingActivity
import com.example.travelbus.views.adapter.activities.BusHireActivity
import com.example.travelbus.views.adapter.adapters.PlacesAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.properties.Delegates

class HomeFragment(private val clickListener: PlacesAdapter.ClickListener) : Fragment(R.layout.fragment_home) {

    private var area : String? = ""
    private var address : String? = ""
    private var sourceDestinationFlag : Int? = null
    private var sourceText = ""
    private var destinationText = ""
    private val db = Firebase.firestore
    private val bookingRef = db.collection("bookings")
    private val userRef = db.collection("users")
    private lateinit var auth: FirebaseAuth

    //current Month Year
    private lateinit var calendar: Calendar
    private var currentMonth by Delegates.notNull<Int>()
    private var currentDay by Delegates.notNull<Int>()
    private var dayOfWeek by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        calendar = Calendar.getInstance()
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        currentMonth = calendar.get(Calendar.MONTH)
        currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        Log.d("abhishek", "$currentDay $dayOfWeek $currentMonth")
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
            setBookingIdInUser(id)
            bookingModel.bus_Id = ""
            bookingRef.document(id).set(bookingModel)
            val intent = Intent(context,BusBookingActivity::class.java)
            startActivity(intent)

        }


        ivSwap.setOnClickListener {
            val sourceLocation: String = etEnterSource.text.toString()
            val destinationLocation: String = etEnterDestination.text.toString()

            etEnterDestination.setText(sourceLocation)
            etEnterSource.setText(destinationLocation)
        }

        val monthName = arrayOf("Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec")
        val weekName = arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")


        tvToday.setOnClickListener {
            var day = (dayOfWeek-1) % 7
            var dayName = weekName[day]
            var month_Name =  monthName[currentMonth]
            etDate.setText("$dayName, $currentDay $month_Name")
        }

        tvTomorrow.setOnClickListener {
            var day = (dayOfWeek) % 7
            var dayName = weekName[day]
            var month_Name =  monthName[currentMonth]
            etDate.setText("$dayName, ${currentDay+1} $month_Name")
        }

        cvBusHire.setOnClickListener {
            startActivity(Intent(context,BusHireActivity::class.java))

        }

        etDate.setOnClickListener {

        }

    }

    private fun setBookingIdInUser(booking_id: String) {
        auth.currentUser?.uid.let { id ->
            if (id != null) {
                userRef.document(id).get().addOnSuccessListener { doc->
                    if (doc.data?.get("bookings") != null) {
                        userRef.document(id).update("bookings", booking_id)
                        //Log.d("abhishek", bookings.toString())

                    } else {
//                        val list: ArrayList<String> = ArrayList<String>()
//                        list.add(booking_id)
                        userRef.document(id).update("bookings", booking_id)
                    }
                }
            }
        }


        // Bus Hire

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