package com.example.travelbus.views.adapter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelbus.R
import com.example.travelbus.models.local.BoardingDropping

class BoardingDroppingAdapter(
    private val borDroppingList:ArrayList<BoardingDropping>
): RecyclerView.Adapter<BoardingDroppingAdapter.BoardingDroppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardingDroppingViewHolder {
        return BoardingDroppingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.boardinglist_itemlayout,parent,false)
        )
    }

    override fun onBindViewHolder(holder: BoardingDroppingViewHolder, position: Int) {
       holder.setData(borDroppingList[position])
    }

    override fun getItemCount(): Int = borDroppingList.size


    class BoardingDroppingViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun setData(data: BoardingDropping){
            itemView.apply {

            }
        }
    }
}