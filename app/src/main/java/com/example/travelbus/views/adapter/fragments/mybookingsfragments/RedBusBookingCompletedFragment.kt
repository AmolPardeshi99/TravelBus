package com.example.travelbus.views.adapter.fragments.mybookingsfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelbus.R
import com.example.travelbus.models.local.BookingDetails
import com.example.travelbus.views.adapter.adapters.BookingListAdapter
import kotlinx.android.synthetic.main.fragment_r_pool_booking_completed.*
import kotlinx.android.synthetic.main.fragment_red_bus_booking_completed.*

class RedBusBookingCompletedFragment : Fragment(R.layout.fragment_red_bus_booking_completed) {

    val bookingList = ArrayList<BookingDetails>()
//    lateinit var bookingListAdapter :BookingListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        bookingListAdapter = BookingListAdapter(bookingList)
        setAdapter()
    }

    private fun setAdapter() {
        bookingList.add(BookingDetails("22 Sept 2021, 03:00 PM, Sunday", "Pune", "Mumbai", "149.3kms", "299/- Paid Via Phonepe"))
        val bookingListAdapter = BookingListAdapter(bookingList)
        val linearLayoutManager  = LinearLayoutManager(activity)

        recyclerViewRedBusCompleted.layoutManager = linearLayoutManager
        recyclerViewRedBusCompleted.adapter = bookingListAdapter
        bookingListAdapter.notifyDataSetChanged()
    }

}