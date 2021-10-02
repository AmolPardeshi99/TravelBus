package com.example.travelbus.views.adapter.adapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.travelbus.R
import com.example.travelbus.models.local.Seats
import com.example.travelbus.views.adapter.base.BaseItemViewHolder

class BusSeatViewHolder(val view: View, val seatClickedListener: SeatClickedListener) :
    BaseItemViewHolder<Seats>(view) {


    override fun setData(data: Seats) {
        val seatIcon = view.findViewById<ImageView>(R.id.imageSeat)
        val seat = view.findViewById<LinearLayout>(R.id.seatItem)
        setSeatIcon(data, seatIcon, seat)

        seat.isSelected = false

        seat.setOnClickListener {
            if (seat.isSelected) {
                seat.isSelected = false
                seatIcon.setBackgroundResource(R.drawable.ic_seat_selected)
                seatClickedListener.onSeatSelected()
            } else {
                seat.isSelected = true
                setSeatIcon(data, seatIcon, seat)
                seatClickedListener.onSeatDeselected()
            }
        }
    }

    private fun setSeatIcon(data: Seats, seatIcon: ImageView, seat: LinearLayout) {
        Log.d("amol", "setSeatIcon: ${data.real_seat+""+data.available+""+data.seat_no+""+data.type}")
        if (data.real_seat.equals("yes")) {
            if (data.type == "female") {
                if (data.available == "yes") {
                    seatIcon.setBackgroundResource(R.drawable.ic_seat_female_available)
                } else {
                    seat.isClickable = false
                    seatIcon.isClickable = false
                    seatIcon.setBackgroundResource(R.drawable.ic_seat_female_booked)
                }
            } else {
                if (data.available == "yes") {
                    seatIcon.setBackgroundResource(R.drawable.ic_seat_available)
                } else {
                    seat.isClickable = false
                    seatIcon.isClickable = false
                    seatIcon.setBackgroundResource(R.drawable.ic_seat_booked)
                }
            }
        }else{
            seat.isClickable = false
            seatIcon.isClickable = false
        }
    }


}


