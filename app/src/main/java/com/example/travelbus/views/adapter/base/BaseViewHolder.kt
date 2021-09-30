package com.example.travelbus.views.adapter.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseItemViewHolder<T:Any>(view: View): RecyclerView.ViewHolder(view){
    abstract fun setData(data:T)
}