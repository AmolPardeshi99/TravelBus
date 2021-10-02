package com.example.travelbus.views.adapter.adapters

import android.view.View
import android.widget.TextView
import com.example.travelbus.R
import com.example.travelbus.models.local.BookingDetails
import com.example.travelbus.views.adapter.base.BaseItemViewHolder

class BookingListViewHolder(val view: View): BaseItemViewHolder<BookingDetails>(view) {

    override fun setData(data: BookingDetails) {
        view.findViewById<TextView>(R.id.tvBookingDate).text = data.date
        view.findViewById<TextView>(R.id.tvSource).text = data.source
        view.findViewById<TextView>(R.id.tvDestination).text = data.destination
        view.findViewById<TextView>(R.id.tvDistance).text = data.distance
        view.findViewById<TextView>(R.id.tvPaymentDetail).text = data.payment
    }
}