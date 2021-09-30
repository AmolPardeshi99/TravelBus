package com.example.travelbus.views.adapter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelbus.R
import com.example.travelbus.models.local.Buses
import kotlinx.android.synthetic.main.itemlayout_bus.view.*

class BusAdapter(
    private val listOfBuses: ArrayList<Buses>,
    var onBusItemClickListener: OnBusItemClickListener
) : RecyclerView.Adapter<BusAdapter.BusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusViewHolder {
        return BusViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.itemlayout_bus, parent, false),
            onBusItemClickListener
        )
    }

    override fun onBindViewHolder(holder: BusViewHolder, position: Int) {
        val data = listOfBuses[position]
        holder.setData(data)
    }

    override fun getItemCount(): Int = listOfBuses.size


    class BusViewHolder(itemView: View, val onBusItemClickListener: OnBusItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        fun setData(data: Buses) {
            itemView.apply {
                tvBusName.text = data.name
                tvBusDesc.text = data.description
                tvBusBoarding.text = data.from
                tvBusDestination.text = data.to
                tvDepArrTime.text = data.timing
                tvTotalTime.text = data.travellingtime
                tvNoOfSeats.text = data.seats + " seats"
                tvPrise.text = "₹ " + data.price
                busBookingCard.setOnClickListener {
                    onBusItemClickListener.onBusClicked(data)
                }
            }
        }
    }
}