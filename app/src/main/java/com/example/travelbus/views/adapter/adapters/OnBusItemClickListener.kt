package com.example.travelbus.views.adapter.adapters

import com.example.travelbus.models.local.Buses

interface OnBusItemClickListener {

    fun onBusClicked(buses: Buses)
}