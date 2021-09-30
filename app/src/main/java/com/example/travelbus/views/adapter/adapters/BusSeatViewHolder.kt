package com.example.travelbus.views.adapter.adapters

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.graphics.drawable.toDrawable
import com.example.travelbus.R
import com.example.travelbus.views.adapter.base.BaseItemViewHolder
import com.example.travelbus.views.adapter.fragments.BusSeat

class BusSeatViewHolder(val view: View) :
    BaseItemViewHolder<BusSeat>(view) {


    override fun setData(data: BusSeat) {
        val seatIcon = view.findViewById<ImageView>(R.id.imageSeat)
        val seat = view.findViewById<LinearLayout>(R.id.seatItem)
        setSeatIcon(data, seatIcon, seat)

        seat.isSelected = false

        seat.setOnClickListener {
            if (seat.isSelected) {
                seat.isSelected = false
                seatIcon.setBackgroundResource(R.drawable.ic_seat_selected)
            } else {
                seat.isSelected = true
                setSeatIcon(data, seatIcon, seat)
            }
        }

    }

    private fun setSeatIcon(data: BusSeat, seatIcon: ImageView, seat: LinearLayout) {
        if (data.Type == "Female") {
            if (data.Availability == "Yes") {
                seatIcon.setBackgroundResource(R.drawable.ic_seat_female_available)
            } else {
                seat.isClickable = false
                seatIcon.isClickable = false
                seatIcon.setBackgroundResource(R.drawable.ic_seat_female_booked)
            }
        } else {
            if (data.Availability == "Yes") {
                seatIcon.setBackgroundResource(R.drawable.ic_seat_available)
            } else {
                seat.isClickable = false
                seatIcon.isClickable = false
                seatIcon.setBackgroundResource(R.drawable.ic_seat_booked)
            }
        }
    }

}


