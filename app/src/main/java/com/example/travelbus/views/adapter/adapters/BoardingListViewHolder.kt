package com.example.travelbus.views.adapter.adapters

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.travelbus.R
import com.example.travelbus.views.adapter.base.BaseItemViewHolder
import com.example.travelbus.views.adapter.fragments.BoardingPoint

class BoardingListViewHolder(val view: View, val clickListener: PlacesAdapter.ClickListener, val sourceDestinationFlag : Int): BaseItemViewHolder<BoardingPoint>(view) {


    override fun setData(data: BoardingPoint) {
        view.findViewById<TextView>(R.id.tvBoardingPoint).text = data.area
        view.findViewById<TextView>(R.id.tvBoardingCity).text = data.address

        view.findViewById<ConstraintLayout>(R.id.itemBoarding).setOnClickListener {
            clickListener.boardingItemClicked(data, sourceDestinationFlag)
        }
    }
}