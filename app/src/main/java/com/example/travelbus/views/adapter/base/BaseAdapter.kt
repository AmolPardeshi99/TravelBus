package com.example.travelbus.views.adapter.base

import androidx.recyclerview.widget.RecyclerView

//Generic class ->  we can pass data type as a variable
abstract class BaseAdapter<T:Any,VH:BaseItemViewHolder<T>>(private val dataList:ArrayList<T>) : RecyclerView.Adapter<VH>() {

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.setData(data = dataList[position])
    }
}