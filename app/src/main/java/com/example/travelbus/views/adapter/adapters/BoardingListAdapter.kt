package com.example.travelbus.views.adapter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.travelbus.R
import com.example.travelbus.views.adapter.base.BaseAdapter
import com.example.travelbus.views.adapter.fragments.BoardingPoint

class BoardingListAdapter (val boardingList: ArrayList<BoardingPoint>, val clickListener: PlacesAdapter.ClickListener, val sourceDestinationFlag : Int): BaseAdapter<BoardingPoint, BoardingListViewHolder>(boardingList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardingListViewHolder =
        BoardingListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.boarding_point_item_layout, parent, false), clickListener, sourceDestinationFlag)
}