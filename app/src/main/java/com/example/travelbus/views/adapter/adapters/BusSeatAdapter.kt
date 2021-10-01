package com.example.travelbus.views.adapter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.travelbus.R
import com.example.travelbus.models.local.Seats
import com.example.travelbus.views.adapter.base.BaseAdapter

class BusSeatAdapter (val busSeats: ArrayList<Seats>, val seatClickedListener: SeatClickedListener): BaseAdapter<Seats, BusSeatViewHolder>(busSeats) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusSeatViewHolder =
        BusSeatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.bus_seat_item_layout, parent, false), seatClickedListener)
}

