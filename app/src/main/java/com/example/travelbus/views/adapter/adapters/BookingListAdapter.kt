package com.example.travelbus.views.adapter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.travelbus.R
import com.example.travelbus.models.local.BookingDetails
import com.example.travelbus.views.adapter.base.BaseAdapter

class BookingListAdapter (val bookingList: ArrayList<BookingDetails>) : BaseAdapter<BookingDetails, BookingListViewHolder> (bookingList){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : BookingListViewHolder =
        BookingListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.booking_item_layout, parent, false))

}