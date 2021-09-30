package com.example.travelbus.views.adapter.adapters

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.travelbus.R
import com.example.travelbus.views.adapter.base.BaseItemViewHolder

class CityListViewHolder(val view: View, val clickListener: PlacesAdapter.ClickListener, val sourceDestinationFlag : Int): BaseItemViewHolder<String>(view) {

    override fun setData(data: String) {
        view.findViewById<TextView>(R.id.tvCity).text = data

        view.findViewById<ConstraintLayout>(R.id.itemCity).setOnClickListener {
            clickListener.cityItemClicked(data, sourceDestinationFlag)
        }
    }
}