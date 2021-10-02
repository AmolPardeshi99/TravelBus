package com.example.travelbus.views.adapter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelbus.R
import com.example.travelbus.models.local.BoardingDropping
import kotlinx.android.synthetic.main.boardinglist_itemlayout.view.*
import kotlinx.android.synthetic.main.itemlayout_bus.view.*

class BoardingDroppingAdapter(
    private val borDroppingList:ArrayList<BoardingDropping>, val onBardingTabClicked: OnBardingTabClicked
): RecyclerView.Adapter<BoardingDroppingAdapter.BoardingDroppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardingDroppingViewHolder {
        return BoardingDroppingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.boardinglist_itemlayout,parent,false),onBardingTabClicked
        )
    }

    override fun onBindViewHolder(holder: BoardingDroppingViewHolder, position: Int) {
       holder.setData(borDroppingList[position])
    }

    override fun getItemCount(): Int = borDroppingList.size


    class BoardingDroppingViewHolder(itemView: View, val onBardingTabClicked: OnBardingTabClicked):RecyclerView.ViewHolder(itemView){

        fun setData(data: BoardingDropping){
            itemView.apply {


                btnRadio.setOnClickListener {
                    onBardingTabClicked.onTabClicked()
                }
            }

        }
    }
}