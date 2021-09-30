package com.example.travelbus.views.adapter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.travelbus.R
import com.example.travelbus.views.adapter.base.BaseAdapter

class CityListAdapter(private val cityList:ArrayList<String>, val clickListener: PlacesAdapter.ClickListener) : BaseAdapter<String, CityListViewHolder>(cityList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListViewHolder =
        CityListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cities_item_layout, parent, false), clickListener)
}